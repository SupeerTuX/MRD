package com.example.mrd.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrd.DataModel.ClienteData;
import com.example.mrd.DataModel.ExteriorData;
import com.example.mrd.DataModel.InteriorData;
import com.example.mrd.DataModel.MotorData;
import com.example.mrd.DataModel.ReportModel;
import com.example.mrd.DataModel.ResponseModel;
import com.example.mrd.Network.GetAPI;
import com.example.mrd.Network.NetworkClient;
import com.example.mrd.Network.PostAPI;
import com.example.mrd.Network.UploadAPI;
import com.example.mrd.R;
import com.example.mrd.Utilidades.FillReporte;
import com.example.mrd.Utilidades.HttpCODES;
import com.example.mrd.Utilidades.PrintBitmap;
import com.example.mrd.Utilidades.StringUtils;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.HTTP;

public class CapturaActivity extends AppCompatActivity {

    static final int CODE_CLIENTE = 1;
    static final int CODE_EXTERIOR = 2;
    static final int CODE_INTERIOR = 3;
    static final int CODE_MOTOR = 4;
    static final int CODE_REPORTE = 6;
    private static final int REQUEST_DISPOSITIVO = 425;
    private static final int FORMAT_TICKET = 5;
    private static final String TAG_DEBUG = "tag_debug";
    private static final int LIMITE_CARACTERES_POR_LINEA = 47;
    private final int ANCHO_IMG_58_MM = 450;
    private static final int MODE_PRINT_IMG = 0;
    private static final int SEPARADOR_TICKET = 3;

    ClienteData clienteData = null;
    ExteriorData exteriorData = null;
    InteriorData interiorData = null;
    MotorData motorData = null;
    ReportModel reportModel = null;

    int[] ResultadoFormulario;
    ArrayList<String> ticketText;
    ArrayList<String> rutasImg;

    private volatile boolean pararLectura;

    private Button btnCliente;
    private Button btnExterior;
    private Button btnInterior;
    private Button btnMotor;
    private Button btnReporteFotografico;
    private Button btnGetFolio;

    private ImageView imgClienteOK;
    private ImageView imgExteriorOK;
    private ImageView imgInteriorOK;
    private ImageView imgMotorOK;
    private ImageView imgReporteOK;

    private ImageButton ibClienteEdit;
    private ImageButton ibExteriorEdit;
    private ImageButton ibInteriorEdit;
    private ImageButton ibMotorEdit;
    private ImageButton ibtnReporteEdit;
    private ImageButton ibtnSincronizar;
    private ImageButton ibtnPrint;
    private ImageButton ibtnUpload;

    // Para la operaciones con dispositivos bluetooth
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice dispositivoBluetooth;
    private BluetoothSocket bluetoothSocket;

    private TextView tvBT;
    private TextView tvPrint;
    private TextView tvUpload;
    private TextView tvTitulo;
            

    // identificador unico default
    private UUID aplicacionUUID = UUID
            .fromString("00001101-0000-1000-8000-00805F9B34FB");
    // Para el flujo de datos de entrada y salida del socket bluetooth
    private OutputStream outputStream;
    private InputStream inputStream;

    /**########################################################
     * + Funcion: Resultado de las Activitis
     #########################################################*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODE_CLIENTE) {
            if (data != null) {
                Bundle objRecivido = data.getExtras();
                clienteData = (ClienteData) objRecivido.getSerializable("cliente");
                imgClienteOK.setVisibility(View.VISIBLE);
                ibClienteEdit.setVisibility(View.VISIBLE);
                //btnCliente.setVisibility(View.INVISIBLE);
                btnCliente.setEnabled(false);
                //Guardamos el resultado de la activyty
                ResultadoFormulario[0] = resultCode;
                //Verificar boton de impresora
                VerificarFormulario();

                //Toast.makeText(CapturaActivity.this, "Validacion OK", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == CODE_EXTERIOR) {
            if (data != null) {
                Bundle objRecivido = data.getExtras();
                exteriorData = (ExteriorData) objRecivido.getSerializable("exterior");
                imgExteriorOK.setVisibility(View.VISIBLE);
                ibExteriorEdit.setVisibility(View.VISIBLE);
                btnExterior.setEnabled(false);
                //Guardamos el resultado de la activyty
                ResultadoFormulario[1] = resultCode;
                //Verificar boton de impresora
                VerificarFormulario();
            }
        }

        if (requestCode == CODE_INTERIOR) {
            if (data != null) {
                Bundle objRecivido = data.getExtras();
                interiorData = (InteriorData) objRecivido.getSerializable("interior");
                imgInteriorOK.setVisibility(View.VISIBLE);
                ibInteriorEdit.setVisibility(View.VISIBLE);
                btnInterior.setEnabled(false);
                //Guardamos el resultado de la activyty
                ResultadoFormulario[2] = resultCode;
                //Verificar boton de impresora
                VerificarFormulario();
            }
        }

        if (requestCode == CODE_MOTOR) {
            if (data != null) {
                Bundle objRecivido = data.getExtras();
                motorData = (MotorData) objRecivido.getSerializable("motor");
                imgMotorOK.setVisibility(View.VISIBLE);
                ibMotorEdit.setVisibility(View.VISIBLE);
                btnMotor.setEnabled(false);
                //Guardamos el resultado de la activyty
                ResultadoFormulario[3] = resultCode;
                //Verificar boton de impresora
                VerificarFormulario();

            }
        }
        //Bluetooth Sincronizacion
        if (requestCode == REQUEST_DISPOSITIVO) {
            if (data != null) {
                final String direccionDispositivo = data.getExtras().getString("DireccionDispositivo");
                final String nombreDispositivo = data.getExtras().getString("NombreDispositivo");
                Toast.makeText(this, "Conectando BT: " + nombreDispositivo + " MAC: " + direccionDispositivo, Toast.LENGTH_LONG).show();

                // Obtenemos el dispositivo con la direccion seleccionada en la lista
                dispositivoBluetooth = bluetoothAdapter.getRemoteDevice(direccionDispositivo);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Conectamos los dispositivos
                            // Creamos un socket
                            bluetoothSocket = dispositivoBluetooth.createRfcommSocketToServiceRecord(aplicacionUUID);
                            bluetoothSocket.connect();// conectamos el socket
                            outputStream = bluetoothSocket.getOutputStream();
                            inputStream = bluetoothSocket.getInputStream();

                            //empezarEscucharDatos();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvBT.setText(nombreDispositivo + " conectada");
                                    Toast.makeText(CapturaActivity.this, "Dispositivo Conectado", Toast.LENGTH_SHORT).show();
                                    //Cambiamos la imagen del boton BT
                                    ibtnSincronizar.setImageDrawable(getResources().getDrawable(R.drawable.bt_on100));
                                    //Cambiamos el texto del tv imprimir
                                    tvPrint.setText("Impresora List");

                                }
                            });

                        } catch (IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvBT.setText("");
                                    Toast.makeText(CapturaActivity.this, "No se pudo conectar el dispositivo", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Log.e(TAG_DEBUG, "Error al conectar el dispositivo bluetooth");

                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
        //Imprimir
        if (requestCode == FORMAT_TICKET){
            Bundle extras = data.getExtras();
            if(extras != null){
                ticketText = extras.getStringArrayList("ticketFormat");

                Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.logo);
                printLogo(bitmap);
                //Inicializacion de la impresora
                printTextInit();
                //Imprimir ticket
                for (int i = 0; i< ticketText.size(); i++){
                    printText(StringUtils.center(ticketText.get(i), LIMITE_CARACTERES_POR_LINEA), 0,0,0,0);
                }
                //separador de ticket
                for (int i = 0; i< SEPARADOR_TICKET; i++){
                    printText(" ", 0,0,0,0);
                }
            }
        }
        //Reporte fotografico
        if (requestCode == CODE_REPORTE){
            if (data != null){
                Bundle extras = data.getExtras();
                rutasImg = extras.getStringArrayList("reporte");
                //Mostramos icono reporte OK
                imgReporteOK.setVisibility(View.VISIBLE);
                //Deshabilitamos el boton Reporte fotografico
                btnReporteFotografico.setEnabled(false);
                //Guardamos el resultado de la activyty
                ResultadoFormulario[4] = resultCode;
                //Habilitamoe el boton de edicion
                ibtnReporteEdit.setVisibility(View.VISIBLE);
                //Verificar boton de impresora
                VerificarFormulario();
            }
        }
    }

    /**########################################################
     * + Metodo onCreate
     #########################################################*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura);

        ResultadoFormulario = new int[5];

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        btnCliente = findViewById(R.id.buttonCliente);
        btnExterior = findViewById(R.id.buttonExterior);
        btnInterior = findViewById(R.id.buttonInterior);
        btnMotor = findViewById(R.id.buttonMotor);
        imgClienteOK = findViewById(R.id.imageViewClienteOK);
        ibClienteEdit = findViewById(R.id.imageButtonClienteEdit);
        btnReporteFotografico = findViewById(R.id.buttonReporteF);
        btnGetFolio = findViewById(R.id.buttonFolio);

        ibtnPrint = findViewById(R.id.imageButtonPrint);
        ibtnSincronizar = findViewById(R.id.imageButtonBT);
        ibtnUpload = findViewById(R.id.imageButtonUpload);

        imgClienteOK = findViewById(R.id.imageViewClienteOK);
        imgExteriorOK = findViewById(R.id.imageViewExteriorOK);
        imgInteriorOK = findViewById(R.id.imageViewInteriorOK);
        imgMotorOK = findViewById(R.id.imageViewMotorOK);
        imgReporteOK = findViewById(R.id.imageViewReporteOK);

        ibClienteEdit = findViewById(R.id.imageButtonClienteEdit);
        ibExteriorEdit = findViewById(R.id.imageButtonExteriorEdit);
        ibInteriorEdit = findViewById(R.id.imageButtonInteriorEdit);
        ibMotorEdit = findViewById(R.id.imageButtonMotorEdit);
        ibtnReporteEdit = findViewById(R.id.imageButtonReporteEdit);

        tvBT = findViewById(R.id.textViewBT);
        tvPrint = findViewById(R.id.textViewPrint);
        tvUpload = findViewById(R.id.textViewUpload);
        tvTitulo = findViewById(R.id.textViewTitulo);


        ibtnPrint.setEnabled(false);
        ibtnUpload.setEnabled(false);


        //Evento boton cliente
        btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CapturaActivity.this, ClienteActivity.class);
                startActivityForResult(intent, CODE_CLIENTE);
            }
        });

        //Evento boton Exterior
        btnExterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CapturaActivity.this, ExteriorActivity.class);
                startActivityForResult(intent, CODE_EXTERIOR);
            }
        });

        //Evento boton Interior
        btnInterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CapturaActivity.this, InteriorActivity.class);
                startActivityForResult(intent, CODE_INTERIOR);
            }
        });

        //Evento Motor
        btnMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CapturaActivity.this, MotorActivity.class);
                startActivityForResult(intent, CODE_MOTOR);
            }
        });
        //Evento Repprte Fotografico
        btnReporteFotografico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CapturaActivity.this, ReporteActivity.class);
                startActivityForResult(intent, CODE_REPORTE);
            }
        });

        //Evento Cliente Editar
        ibClienteEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editModule(CODE_CLIENTE);
                ResultadoFormulario[0] = 0;
            }
        });

        //Evento Exterior Editar
        ibExteriorEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editModule(CODE_EXTERIOR);
                ResultadoFormulario[1] = 0;
            }
        });

        //Evento Interior Editar
        ibInteriorEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editModule(CODE_INTERIOR);
                ResultadoFormulario[2] = 0;
            }
        });

        //Evento Motor Editar
        ibMotorEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editModule(CODE_MOTOR);
                ResultadoFormulario[3] = 0;
            }
        });

        //Evento Reporte Edit
        ibtnReporteEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editModule(CODE_REPORTE);
                for (int i = 0; i< rutasImg.size(); i++){
                    rutasImg.set(i, "");
                }
            }
        });


        //Evento Sincornizar impresora
        ibtnSincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CapturaActivity.this, ListaBTActivity.class);
                startActivityForResult(intent, REQUEST_DISPOSITIVO);
            }
        });

        //Evento imprimir
        ibtnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothSocket != null) {

                    Intent intent =  new Intent(CapturaActivity.this, TicketActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ticket", clienteData);
                    bundle.putSerializable("exterior", exteriorData);
                    bundle.putSerializable("interior", interiorData);
                    bundle.putSerializable("motor", motorData);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, FORMAT_TICKET);

                } else {
                    Log.e(TAG_DEBUG, "Socket nulo");
                    tvPrint.setText("Sin Conexion");
                    Toast.makeText(CapturaActivity.this, "Impresora sin conexion", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Evento Upload Reporte
        ibtnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Uploadd Reporte
                //LLenamos el reporte
                reportModel = new ReportModel();
                FillReporte fillReporte =  new FillReporte(clienteData, reportModel, exteriorData, interiorData, motorData);
                reportModel = fillReporte.fill();
                //Subimos el reporte por POST al servidor
                postReporte();
            }
        });

        //Boton para generar Folio
        btnGetFolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generarFolio();
            }
        });

    }

    /**########################################################
     * + Funcion: Cierra la conexion BT
     #########################################################*/
    private void cerrarConexion() {
        try {
            if (bluetoothSocket != null) {
                if (outputStream != null) outputStream.close();
                pararLectura = true;
                if (inputStream != null) inputStream.close();
                bluetoothSocket.close();
                Toast.makeText(CapturaActivity.this, "Conexion Terminada", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**########################################################
     * + Funcion: Habilita los botones de edicion de formulario
     #########################################################*/
    private void editModule(final int CODE) {
        new AlertDialog.Builder(CapturaActivity.this)
                .setIcon(R.drawable.edit_icon)
                .setTitle("¿Modulo Completo, Desea Editarlo?")
                .setCancelable(false)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {// un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //android.os.Process.killProcess(android.os.Process.myPid()); //Su funcion es algo similar a lo que se llama cuando se presiona el botón "Forzar Detención" o "Administrar aplicaciones", lo cuál mata la aplicación
                        //finish(); Si solo quiere mandar la aplicación a segundo plano
                        switch (CODE) {
                            case CODE_CLIENTE:
                                btnCliente.setEnabled(true);
                                imgClienteOK.setVisibility(View.INVISIBLE);
                                ibClienteEdit.setVisibility(View.INVISIBLE);
                                break;
                            case CODE_EXTERIOR:
                                btnExterior.setEnabled(true);
                                imgExteriorOK.setVisibility(View.INVISIBLE);
                                ibExteriorEdit.setVisibility(View.INVISIBLE);
                                break;
                            case CODE_INTERIOR:
                                btnInterior.setEnabled(true);
                                imgInteriorOK.setVisibility(View.INVISIBLE);
                                ibInteriorEdit.setVisibility(View.INVISIBLE);
                                break;
                            case CODE_MOTOR:
                                btnMotor.setEnabled(true);
                                imgMotorOK.setVisibility(View.INVISIBLE);
                                ibMotorEdit.setVisibility(View.INVISIBLE);
                                break;
                            case CODE_REPORTE:
                                btnReporteFotografico.setEnabled(true);
                                imgReporteOK.setVisibility(View.INVISIBLE);
                                ibtnReporteEdit.setVisibility(View.INVISIBLE);
                                break;
                        }
                    }
                }).show();
    }

    /**########################################################
     * + Funcion: Tranforma texto en bytes para ser enviado
     * + a la impresora
     #########################################################*/
    /**
     * (font:A font:B)
     *
     * @param str
     * @param bold
     * @param font
     * @param widthsize
     * @param heigthsize
     * @return
     */
    public static byte[] getByteString(String str, int bold, int font, int widthsize, int heigthsize) {

        if (str.length() == 0 | widthsize < 0 | widthsize > 3 | heigthsize < 0 | heigthsize > 3
                | font < 0 | font > 1)
            return null;

        byte[] strData = null;
        try {
            strData = str.getBytes("iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        byte[] command = new byte[strData.length + 9];

        byte[] intToWidth = {0x00, 0x10, 0x20, 0x30};//
        byte[] intToHeight = {0x00, 0x01, 0x02, 0x03};//

        command[0] = 27;// caracter ESC para darle comandos a la impresora
        command[1] = 69;
        command[2] = ((byte) bold);
        command[3] = 27;
        command[4] = 77;
        command[5] = ((byte) font);
        command[6] = 29;
        command[7] = 33;
        command[8] = (byte) (intToWidth[widthsize] + intToHeight[heigthsize]);

        System.arraycopy(strData, 0, command, 9, strData.length);
        return command;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cerrarConexion();
    }
    /**########################################################
     * + Funcion: Inicializacion de la impresora
     #########################################################*/
    public void  printTextInit() {
        try {
            // Para que acepte caracteres espciales
            outputStream.write(0x1C);
            outputStream.write(0x2E); // Cancelamos el modo de caracteres chino (FS .)
            outputStream.write(0x1B);
            outputStream.write(0x74);
            outputStream.write(0x10); // Seleccionamos los caracteres escape (ESC t n) - n = 16(0x10) para WPC1252
        } catch (IOException e) {
            Log.e(TAG_DEBUG, "Error al escribir en el socket");
            Toast.makeText(CapturaActivity.this, "Error al iniciar la impresion", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    /**########################################################
     * + Funcion: Primitiva que imprime una sola linea de texto
     #########################################################*/
    public void printText(String texto, int negrita, int fuente, int ancho, int alto) {
        try {
            outputStream.write(getByteString(texto, negrita, fuente, ancho, alto));
            outputStream.write("\n".getBytes());
        } catch (IOException e) {
            Log.e(TAG_DEBUG, "Error al escribir en el socket");
            Toast.makeText(CapturaActivity.this, "Error al interntar imprimir texto", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }

    /**########################################################
     * + Funcion: Imprime el logo del ticket
     #########################################################*/
    public void printLogo(Bitmap bitmap){
        try {
            outputStream.write(PrintBitmap.POS_PrintBMP(bitmap, ANCHO_IMG_58_MM, MODE_PRINT_IMG));
            outputStream.write("\n\n".getBytes());
        }catch (IOException e){
            Toast.makeText(CapturaActivity.this, "Error al intentar imprimir imagen", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**########################################################
     * + Funcion: Verifica si todos los formularios han sido
     * + llenados
     #########################################################*/
    private void  VerificarFormulario(){
        //Verificar si todas los formularios esta llenos
        for (int i = 0; i < ResultadoFormulario.length; i++){
            if (ResultadoFormulario[i] != Activity.RESULT_OK){
                return;
            }
        }
        //Se han llenado todos los formularios, se procede a generar el folio del servidor tomando en cuenta el corralon
        //Verificamos si ya existe un folio
        if (clienteData.getFolio() == null || clienteData.getFolio().isEmpty()){
            generarFolio();
        }else{
            //Habilitar impresion
            habilitarImpresion();
            //habilitamos el boton upload
            ibtnUpload.setEnabled(true);
        }


    }


    /**########################################################
     * + Funcion: Carga las imagenes capturadas al servidor
     #########################################################*/
    private void habilitarImpresion(){
        ibtnPrint.setEnabled(true);
        ibtnPrint.setImageDrawable(getResources().getDrawable(R.drawable.impresora_icon));
    }

    /**########################################################
     * + Funcion: Carga las imagenes capturadas al servidor
     #########################################################*/
    private void uploadToServer() {
        Retrofit retrofit = NetworkClient.getRetrofitClientUpload(this);
        UploadAPI uploadAPI = retrofit.create(UploadAPI.class);
        //Escalamos las imagenes capturadas para enviarlas al servidor
        scaleBitmap();
        File file1 = new File(rutasImg.get(0));
        File file2 = new File(rutasImg.get(1));
        File file3 = new File(rutasImg.get(2));
        File file4 = new File(rutasImg.get(3));

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("example2[]", file1.getName(), RequestBody.create(MediaType.parse("image/*"), file1 ));
        builder.addFormDataPart("example2[]", file2.getName(), RequestBody.create(MediaType.parse("image/*"), file2 ));
        builder.addFormDataPart("example2[]", file3.getName(), RequestBody.create(MediaType.parse("image/*"), file3 ));
        builder.addFormDataPart("example2[]", file4.getName(), RequestBody.create(MediaType.parse("image/*"), file4 ));

        MultipartBody requestBody =  builder.build();

        Call<ResponseBody> call = uploadAPI.uploadImage(requestBody, clienteData.getFolio(), clienteData.getRegion().toLowerCase());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code()== HttpCODES.CREATED){
                    Toast.makeText(CapturaActivity.this, "Fotos enviadas correctamente", Toast.LENGTH_SHORT).show();
                    //Desactivando los botonoes para editar los reportes
                    ibClienteEdit.setEnabled(false);
                    ibExteriorEdit.setEnabled(false);
                    ibInteriorEdit.setEnabled(false);
                    ibMotorEdit.setEnabled(false);
                    ibtnReporteEdit.setEnabled(false);

                    //Deshabilitamos el boton upload
                    ibtnUpload.setEnabled(false);
                }else {
                    Toast.makeText(CapturaActivity.this, "Error al subir el reporte al servidor. Intentelo nuevamente", Toast.LENGTH_SHORT).show();
                }
                //TODO: Mostrar cuadros de dialogo en la operacion subir reporte
                //TODO: Boton cerrar el programa
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(CapturaActivity.this, "Error al subir el reporte al servidor. Intentelo nuevamente", Toast.LENGTH_SHORT).show();
            }
        });

    }


    /**########################################################
     * + Funcion: Carga el reporte por metodo POST
     #########################################################*/
    private void  postReporte(){
        Retrofit retrofit = NetworkClient.getRetrofitClientPost(this);
        PostAPI postAPI = retrofit.create(PostAPI.class);

        Call<ResponseModel> postServiceCall =postAPI.postReporte(reportModel);

        postServiceCall.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                if(response.code() == HttpCODES.CREATED ){
                    Toast.makeText(CapturaActivity.this, "Reporte Creado", Toast.LENGTH_SHORT).show();
                    uploadToServer();
                }else {
                    Toast.makeText(CapturaActivity.this, "Error al subir reporte" + responseModel.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(CapturaActivity.this, "Error subir reporte: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**########################################################
     * + Funcion: Genera Folio en la DB paar poder ingresar la
     * + Informacion en la tabla
     #########################################################*/
    private void generarFolio(){
        //TODO: Prevenir la creacion de folios dobles, al editar un formulario despues de la captura de datos.
        final ProgressDialog progressDialog = new ProgressDialog(CapturaActivity.this);
        progressDialog.setMessage("Validando Usuario"); // Setting Message
        progressDialog.setTitle("Procesando"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);

        new Thread(new Runnable() {
            public void run() {
                try {
                    //Creamos el objeto retrofit
                    final Retrofit retrofit = NetworkClient.getRetrofitClientPost(this);
                    GetAPI getAPI = retrofit.create(GetAPI.class);
                    Call<ResponseModel> getFolioCall = getAPI.getFolio(clienteData.getRegion());

                    getFolioCall.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            //Si el codigo de respuesta es 201 ok
                            if (response.code() == HttpCODES.CREATED){
                                //Recogemos la respuesta del servidor
                                ResponseModel responseModel = response.body();
                                clienteData.setFolio(responseModel.getMsg());
                                //Cambiamos el titulo por el folio y region
                                tvTitulo.setText(clienteData.getFolio() + " " + clienteData.getRegion());
                                //Cambiamos la imagen del botono upload
                                ibtnUpload.setImageDrawable(getResources().getDrawable(R.drawable.upload_on));
                                //Habilitar impresion
                                habilitarImpresion();
                                //habilitamos el boton upload
                                ibtnUpload.setEnabled(true);
                                //Deshabilitamose el boton
                                enableDisableBtnGetFolio(false);
                                progressDialog.dismiss();
                                Toast.makeText(CapturaActivity.this, "Folio Generado Correctamente", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                progressDialog.dismiss();
                                enableDisableBtnGetFolio(true);
                                Toast.makeText(CapturaActivity.this, "Error al generar Folio, intentelo de nuevo", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {
                            progressDialog.dismiss();
                            enableDisableBtnGetFolio(true);
                            Toast.makeText(CapturaActivity.this, "Error al generar Folio, Tiene conexion a Internet??", Toast.LENGTH_SHORT).show();
                        }
                    });

                    Thread.sleep(60000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();
    }


    /**########################################################
     * + Funcion: Baja la calidad de las fotos tomadas por la
     * + camara para ser subidas al servidor
     #########################################################*/
    private void scaleBitmap(){
        OutputStream outStream = null;
        //File file = new File(rutasImg.get(0));

        ArrayList<File> files = new ArrayList<>();

        int m_inSampleSize = 0;
        int m_compress = 70;

        try {
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inPurgeable = true;
            bmOptions.inSampleSize = m_inSampleSize;

            for (int i = 0; i < rutasImg.size(); i++){
                //Creamos la lista de files
                files.add(new File(rutasImg.get(i)));
                //Comprimimos las imagenes del array rutasImg
                Bitmap bitmap = BitmapFactory.decodeFile(rutasImg.get(i), bmOptions);
                outStream = new FileOutputStream(files.get(i));
                bitmap.compress(Bitmap.CompressFormat.JPEG, m_compress, outStream);
                outStream.flush();
                outStream.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void enableDisableBtnGetFolio(boolean enable){
        if (enable)
        btnGetFolio.setVisibility(View.VISIBLE);
        else
            btnGetFolio.setVisibility(View.INVISIBLE);
    }
}
