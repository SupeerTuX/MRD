package com.example.mrd.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mrd.DataModel.MotorData;
import com.example.mrd.R;

public class MotorActivity extends AppCompatActivity {

    private MotorData motorData;

    private Button btnValidar;
    private Spinner[] spinnerEstado = new Spinner[11];

    private EditText etCarga;
    private EditText etObservaciones;
    private EditText etMarcaBateria;
    private SeekBar sbGasolina;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor);

        motorData = new MotorData();
        btnValidar = (Button) findViewById(R.id.buttonMotorValidar);
        etCarga = (EditText) findViewById(R.id.editTextCarga);
        etObservaciones = (EditText) findViewById(R.id.editTextObservaciones);
        etMarcaBateria = (EditText) findViewById(R.id.editTextMaracaBateria);
        sbGasolina = (SeekBar) findViewById(R.id.seekBarGasolina);

        setSpinner();

        ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(MotorActivity.this, R.array.estado_array, R.layout.custom_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //android.R.layout.simple_spinner_item

        for (int i = 0; i < spinnerEstado.length; i++) {
            spinnerEstado[i].setAdapter(adapter);
        }

        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CopyDataToDataModel();
                if (motorData.Validar()){
                    Toast.makeText(MotorActivity.this, "Validacion Correcta", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MotorActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("motor", motorData);
                    intent.putExtras(bundle);
                    setResult(Activity.RESULT_OK, intent);
                    finish();

                }else{
                    Toast.makeText(MotorActivity.this, "Validacion Incorrecta Debe Llenar Todos Los Campos", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void setSpinner(){
        spinnerEstado[0] = (Spinner) findViewById(R.id.spinnerMotor1);
        spinnerEstado[1] = (Spinner) findViewById(R.id.spinnerMotor2);
        spinnerEstado[2] = (Spinner) findViewById(R.id.spinnerMotor3);
        spinnerEstado[3] = (Spinner) findViewById(R.id.spinnerMotor4);
        spinnerEstado[4] = (Spinner) findViewById(R.id.spinnerMotor5);
        spinnerEstado[5] = (Spinner) findViewById(R.id.spinnerMotor6);
        spinnerEstado[6] = (Spinner) findViewById(R.id.spinnerMotor7);
        spinnerEstado[7] = (Spinner) findViewById(R.id.spinnerMotor8);
        spinnerEstado[8] = (Spinner) findViewById(R.id.spinnerMotor9);
        spinnerEstado[9] = (Spinner) findViewById(R.id.spinnerMotor10);
        spinnerEstado[10] = (Spinner) findViewById(R.id.spinnerMotor11);

    }

    private void CopyDataToDataModel(){
        String[] s = new String[spinnerEstado.length];
        for (int i = 0; i < spinnerEstado.length; i++) {
            s[i] = spinnerEstado[i].getSelectedItem().toString();
        }
        motorData.setData(s);


        motorData.setMarcaBateria(etMarcaBateria.getText().toString());
        int progress = sbGasolina.getProgress();
        motorData.setMedidorGasolina(String.valueOf(progress));
        motorData.setMotorCarga(etCarga.getText().toString());
        motorData.setMotorObservaciones(etObservaciones.getText().toString());
    }

}
