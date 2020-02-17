package com.example.mrd.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mrd.DataModel.ExteriorData;
import com.example.mrd.R;

public class ExteriorActivity extends AppCompatActivity {

    private Spinner[] spinnerEstado = new Spinner[21];
    private Button btnValidar;

    private ExteriorData exteriorData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exterior);

        exteriorData = new ExteriorData();


        btnValidar = findViewById(R.id.buttonExteriorValidar);

        //Set Spinner
        setSpinner();

        ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(ExteriorActivity.this, R.array.estado_array, R.layout.custom_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //android.R.layout.simple_spinner_item

        for (int i = 0; i < spinnerEstado.length; i++) {
            spinnerEstado[i].setAdapter(adapter);
        }

        //Evento Boton Validar
        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CopyDataToDataModel();
                Toast.makeText(ExteriorActivity.this, "Validacion Correcta", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ExteriorActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("exterior", exteriorData);

                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }



    public void setSpinner(){
        spinnerEstado[0] = (Spinner) findViewById(R.id.spinnerExt1);
        spinnerEstado[1] = (Spinner) findViewById(R.id.spinnerExt2);
        spinnerEstado[2] = (Spinner) findViewById(R.id.spinnerExt3);
        spinnerEstado[3] = (Spinner) findViewById(R.id.spinnerExt4);
        spinnerEstado[4] = (Spinner) findViewById(R.id.spinnerExt5);
        spinnerEstado[5] = (Spinner) findViewById(R.id.spinnerExt6);
        spinnerEstado[6] = (Spinner) findViewById(R.id.spinnerExt7);
        spinnerEstado[7] = (Spinner) findViewById(R.id.spinnerExt8);
        spinnerEstado[8] = (Spinner) findViewById(R.id.spinnerExt9);
        spinnerEstado[9] = (Spinner) findViewById(R.id.spinnerExt10);
        spinnerEstado[10] = (Spinner) findViewById(R.id.spinnerExt11);
        spinnerEstado[11] = (Spinner) findViewById(R.id.spinnerExt12);
        spinnerEstado[12] = (Spinner) findViewById(R.id.spinnerExt13);
        spinnerEstado[13] = (Spinner) findViewById(R.id.spinnerExt14);
        spinnerEstado[14] = (Spinner) findViewById(R.id.spinnerExt15);
        spinnerEstado[15] = (Spinner) findViewById(R.id.spinnerExt16);
        spinnerEstado[16] = (Spinner) findViewById(R.id.spinnerExt17);
        spinnerEstado[17] = (Spinner) findViewById(R.id.spinnerExt18);
        spinnerEstado[18] = (Spinner) findViewById(R.id.spinnerExt19);
        spinnerEstado[19] = (Spinner) findViewById(R.id.spinnerExt20);
        spinnerEstado[20] = (Spinner) findViewById(R.id.spinnerExt21);

    }

    private void CopyDataToDataModel(){
        String[] s = new String[spinnerEstado.length];
        for (int i = 0; i < spinnerEstado.length; i++) {
            s[i] = spinnerEstado[i].getSelectedItem().toString();
        }
        exteriorData.setData(s);
    }

}
