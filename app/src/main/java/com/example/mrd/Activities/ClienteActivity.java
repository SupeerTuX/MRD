package com.example.mrd.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mrd.DataModel.ClienteData;
import com.example.mrd.R;

public class ClienteActivity extends AppCompatActivity {



    private Button btnValidar;
    private ImageButton ibtnDate;
    private ImageButton ibtnTime;
    private ImageButton ibtnGPS;


    private EditText etDate;
    private EditText etTime;
    private EditText etDireccion;
    private Spinner spinnerInventario;
    private Spinner spinnerCorralon;
    private EditText etVehiculoTipo;
    private EditText etVehiculoModelo;
    private EditText etVehiculoColor;
    private EditText etVehiculoPlacas;
    private EditText etVehiculoSerie;
    private EditText etVehiculoPropietario;
    private Switch swVehiculoLlaves;
    private EditText etTelefono;
    private EditText etOperadorGrua;
    private EditText etOperadorClave;
    private EditText etAutoridad;
    private AutoCompleteTextView acMarca;



    private  String dbDate;
    private  String dbTime;



    //Variables para el calendario
    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final String GUION = "-";
    private static final String DOS_PUNTOS = ":";
    private static final int CODE_GPS = 11;

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Variables para obtener la hora hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);



        btnValidar = findViewById(R.id.buttonValidar);
        ibtnDate = findViewById(R.id.imageButtonDate);
        ibtnTime = findViewById(R.id.imageButtonTime);
        ibtnGPS =findViewById(R.id.imageButtonGps);

        etDate = findViewById(R.id.editTextDate);
        etTime = findViewById(R.id.editTextTime);
        etDireccion = findViewById(R.id.editTextDireccion);
        spinnerInventario = findViewById(R.id.spinnerInventario);
        spinnerCorralon = findViewById(R.id.spinnerCorralon);
        etVehiculoTipo = findViewById(R.id.editTextVehiculoTipo);
        etVehiculoModelo = findViewById(R.id.editTextVehiculoModelo);
        etVehiculoColor = findViewById(R.id.editTextVehiculoColor);
        etVehiculoPlacas = findViewById(R.id.editTextPlacas);
        etVehiculoSerie = findViewById(R.id.editTextSerie);
        etVehiculoPropietario = findViewById(R.id.editTextPropiertario);
        etVehiculoPropietario = findViewById(R.id.editTextPropiertario);
        swVehiculoLlaves = findViewById(R.id.switchLLaves);
        etTelefono = findViewById(R.id.editTextTelefono);
        etOperadorGrua = findViewById(R.id.editTextGrua);
        etOperadorClave = findViewById(R.id.editTextClave);
        etAutoridad = findViewById(R.id.editTextAutoridad);
        acMarca = (AutoCompleteTextView) findViewById(R.id.autoCompleteMarca);


        String[] marcas = getResources().getStringArray(R.array.marca_array);
        ArrayAdapter<String> marcaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, marcas);
        acMarca.setAdapter(marcaAdapter);





        ArrayAdapter<CharSequence> adapterInventario =  ArrayAdapter.createFromResource(ClienteActivity.this, R.array.inventario_array, android.R.layout.simple_spinner_item);
        adapterInventario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInventario.setAdapter(adapterInventario);

        ArrayAdapter<CharSequence> adapterCorralon =  ArrayAdapter.createFromResource(ClienteActivity.this, R.array.corralon_array, android.R.layout.simple_spinner_item);
        adapterCorralon.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCorralon.setAdapter(adapterCorralon);


        //Evento boton validar
        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validar datos del formulario
                ClienteData clienteData = new ClienteData();

                //clienteData.setFolio(etFolio.getText().toString());
                clienteData.setRegion(spinnerCorralon.getSelectedItem().toString());
                clienteData.setDateDB(dbDate + " " + dbTime);
                clienteData.setDate(etDate.getText().toString());
                clienteData.setTime(etTime.getText().toString());
                clienteData.setDireccion(etDireccion.getText().toString());
                clienteData.setMotivoInventario(spinnerInventario.getSelectedItem().toString());
                //*
                clienteData.setVehiculoMarca(acMarca.getText().toString());
                clienteData.setVehiculoTipo(etVehiculoTipo.getText().toString());
                clienteData.setVehiculoModelo(etVehiculoModelo.getText().toString());
                clienteData.setVehiculoColor(etVehiculoColor.getText().toString());
                clienteData.setVehiculoPlacas(etVehiculoPlacas.getText().toString());
                clienteData.setVehiculoSerie(etVehiculoSerie.getText().toString());
                clienteData.setVehiculoPropietario(etVehiculoPropietario.getText().toString());
                clienteData.setVehiculoLlaves(swVehiculoLlaves.isChecked());
                clienteData.setTelefono(etTelefono.getText().toString());
                clienteData.setOperadorGrua(etOperadorGrua.getText().toString());
                clienteData.setOperadorClave(etOperadorClave.getText().toString());
                clienteData.setAutoridad(etAutoridad.getText().toString());


                if (clienteData.Validar()){
                    Toast.makeText(ClienteActivity.this, "Validacion Correcta", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ClienteActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("cliente", clienteData);

                    intent.putExtras(bundle);
                    setResult(Activity.RESULT_OK, intent);
                    finish();

                } else {
                    Toast.makeText(ClienteActivity.this, "Validacion Incorrecta Debe Llenar Todos Los Campos", Toast.LENGTH_LONG).show();
                    //Toast.makeText(ClienteActivity.this, "Debe Llenar Todos Los Campos", Toast.LENGTH_LONG).show();
                }
            }
        });


        //Evento captura de Fecha
        ibtnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerFecha();

            }
        });
        //Evento captura de la hora
        ibtnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerHora();
            }
        });
        //Evento obtener las coordenadas del gps
        ibtnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClienteActivity.this, MapsActivity.class);
                startActivityForResult(intent, CODE_GPS);
            }
        });

    }

    private void obtenerFecha() {

        DatePickerDialog recogerFecha = new DatePickerDialog(ClienteActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                etDate.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                //Obtener formato de fecha para
                dbDate =  year + GUION + mesFormateado + GUION + diaFormateado;
            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();
    }

    private void obtenerHora() {

        TimePickerDialog recogerHora = new TimePickerDialog(ClienteActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                etTime.setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
                dbTime = horaFormateada + DOS_PUNTOS + minutoFormateado + DOS_PUNTOS + "00";
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, true);

        recogerHora.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODE_GPS){
            if(data != null){
                Bundle extras = data.getExtras();
                String direccion = extras.getString("direccion");
                etDireccion.setText(direccion);
            }
        }

    }
}
