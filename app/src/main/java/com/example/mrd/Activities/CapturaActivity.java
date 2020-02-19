package com.example.mrd.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrd.DataModel.ClienteData;
import com.example.mrd.DataModel.ExteriorData;
import com.example.mrd.DataModel.InteriorData;
import com.example.mrd.DataModel.MotorData;
import com.example.mrd.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class CapturaActivity extends AppCompatActivity {

    static final int CODE_CLIENTE = 1;
    static final int CODE_EXTERIOR = 2;
    static final int CODE_INTERIOR = 3;
    static final int CODE_MOTOR = 4;
    private static final int REQUEST_DISPOSITIVO = 425;
    private static final String TAG_DEBUG = "tag_debug";

    ClienteData clienteData = null;
    ExteriorData exteriorData =null;
    InteriorData interiorData =null;
    MotorData motorData = null;

    private volatile boolean pararLectura;

    private Button btnCliente;
    private Button btnExterior;
    private Button btnInterior;
    private Button btnMotor;
    private Button btnSH;
    private Button btnTest;

    private ImageView imgClienteOK;
    private ImageView imgExteriorOK;
    private ImageView imgInteriorOK;
    private ImageView imgMotorOK;

    private ImageButton  ibClienteEdit;
    private ImageButton  ibExteriorEdit;
    private ImageButton  ibInteriorEdit;
    private ImageButton  ibMotorEdit;

    // Para la operaciones con dispositivos bluetooth
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice dispositivoBluetooth;
    private BluetoothSocket bluetoothSocket;

    private TextView tvBT;

    // identificador unico default
    private UUID aplicacionUUID = UUID
            .fromString("00001101-0000-1000-8000-00805F9B34FB");
    // Para el flujo de datos de entrada y salida del socket bluetooth
    private OutputStream outputStream;
    private InputStream inputStream;




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == CODE_CLIENTE){
                if(data != null){
                    Bundle objRecivido = data.getExtras();
                    clienteData = (ClienteData) objRecivido.getSerializable("cliente");
                    imgClienteOK.setVisibility(View.VISIBLE);
                    ibClienteEdit.setVisibility(View.VISIBLE);
                    //btnCliente.setVisibility(View.INVISIBLE);
                    btnCliente.setEnabled(false);

                    //Toast.makeText(CapturaActivity.this, "Validacion OK", Toast.LENGTH_SHORT).show();
                }
        }

        if(requestCode == CODE_EXTERIOR){
            if(data != null){
                Bundle objRecivido = data.getExtras();
                exteriorData = (ExteriorData) objRecivido.getSerializable("exterior");
                imgExteriorOK.setVisibility(View.VISIBLE);
                ibExteriorEdit.setVisibility(View.VISIBLE);
                btnExterior.setEnabled(false);
            }
        }

        if(requestCode == CODE_INTERIOR){
            if(data != null){
                Bundle objRecivido = data.getExtras();
                interiorData = (InteriorData) objRecivido.getSerializable("interior");
                imgInteriorOK.setVisibility(View.VISIBLE);
                ibInteriorEdit.setVisibility(View.VISIBLE);
                btnInterior.setEnabled(false);
            }
        }

        if(requestCode == CODE_MOTOR){
            if(data != null){
                Bundle objRecivido = data.getExtras();
                motorData = (MotorData) objRecivido.getSerializable("motor");
                imgMotorOK.setVisibility(View.VISIBLE);
                ibMotorEdit.setVisibility(View.VISIBLE);
                btnMotor.setEnabled(false);

            }
        }

        if(requestCode == REQUEST_DISPOSITIVO)
            if(data != null){
                final String direccionDispositivo = data.getExtras().getString("DireccionDispositivo");
                final String nombreDispositivo = data.getExtras().getString("NombreDispositivo");
                Toast.makeText(this, "Conectando BT: " + nombreDispositivo + " MAC: "+direccionDispositivo , Toast.LENGTH_LONG).show();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        btnCliente = findViewById(R.id.buttonCliente);
        btnExterior = findViewById(R.id.buttonExterior);
        btnInterior = findViewById(R.id.buttonInterior);
        btnMotor = findViewById(R.id.buttonMotor);
        imgClienteOK = findViewById(R.id.imageViewClienteOK);
        ibClienteEdit = findViewById(R.id.imageButtonClienteEdit);

        btnTest = findViewById(R.id.buttonTest);
        btnSH = findViewById(R.id.buttonSH);

        imgClienteOK = findViewById(R.id.imageViewClienteOK);
        imgExteriorOK= findViewById(R.id.imageViewExteriorOK);
        imgInteriorOK = findViewById(R.id.imageViewInteriorOK);
        imgMotorOK = findViewById(R.id.imageViewMotorOK);

        ibClienteEdit = findViewById(R.id.imageButtonClienteEdit);
        ibExteriorEdit = findViewById(R.id.imageButtonExteriorEdit);
        ibInteriorEdit = findViewById(R.id.imageButtonInteriorEdit);
        ibMotorEdit = findViewById(R.id.imageButtonMotorEdit);

        tvBT = findViewById(R.id.textViewBT);


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

        //Evento Cliente Editar
        ibClienteEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editModule(CODE_CLIENTE);
            }
        });

        //Evento Exterior Editar
        ibExteriorEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editModule(CODE_EXTERIOR);
            }
        });

        //Evento Interior Editar
        ibInteriorEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editModule(CODE_INTERIOR);
            }
        });

        //Evento Motor Editar
        ibMotorEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editModule(CODE_MOTOR);
            }
        });


        //Evento para mostrar el recycler view
        btnSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CapturaActivity.this, ListaBTActivity.class);
                startActivityForResult(intent, REQUEST_DISPOSITIVO);
            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothSocket != null) {
                    try {
                        String texto = "TuXDevelop"+ "\n";
                        /*
                        int fuente = Integer.parseInt(spnFuente.getSelectedItem().toString());
                        int negrita = spnNegrita.getSelectedItem().toString().equals("Si") ? 1 : 0;
                        int ancho = Integer.parseInt(spnAncho.getSelectedItem().toString());
                        int alto = Integer.parseInt(spnAlto.getSelectedItem().toString());*/
                        int fuente = 0;
                        int negrita = 0;
                        int ancho = 0;
                        int alto = 0;

                        // Para que acepte caracteres espciales
                        outputStream.write(0x1C); outputStream.write(0x2E); // Cancelamos el modo de caracteres chino (FS .)
                        outputStream.write(0x1B); outputStream.write(0x74); outputStream.write(0x10); // Seleccionamos los caracteres escape (ESC t n) - n = 16(0x10) para WPC1252

                        outputStream.write( getByteString(texto, negrita, fuente, ancho, alto) );

                        outputStream.write("\n\n".getBytes());

                    } catch (IOException e) {
                        Log.e(TAG_DEBUG, "Error al escribir en el socket");
                        Toast.makeText(CapturaActivity.this, "Error al interntar imprimir texto", Toast.LENGTH_SHORT).show();

                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG_DEBUG, "Socket nulo");

                    tvBT.setText("Impresora no conectada");
                }
            }
        });
    }

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
                        switch (CODE){
                            case CODE_CLIENTE: btnCliente.setEnabled(true); break;
                            case CODE_EXTERIOR: btnExterior.setEnabled(true); break;
                            case CODE_INTERIOR: btnInterior.setEnabled(true); break;
                            case CODE_MOTOR: btnMotor.setEnabled(true); break;
                        }
                    }
                }).show();
    }

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
}
