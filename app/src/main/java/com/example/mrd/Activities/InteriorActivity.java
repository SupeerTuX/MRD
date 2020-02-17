package com.example.mrd.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mrd.DataModel.InteriorData;
import com.example.mrd.R;

public class InteriorActivity extends AppCompatActivity {

    private Spinner[] spinnerEstado = new Spinner[17];
    private Button btnValidar;
    private InteriorData interiorData;
    private EditText etLlantasMarca;
    private EditText etLlantasCantidad;
    private EditText etLlantasMedida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interior);

        interiorData = new InteriorData();
        btnValidar = findViewById(R.id.buttonInteriorValidar);

        setSpinner();
        ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(InteriorActivity.this, R.array.estado_array, R.layout.custom_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //android.R.layout.simple_spinner_item

        etLlantasMarca = findViewById(R.id.editTextLlantasMarca);
        etLlantasCantidad = findViewById(R.id.editTextLlantasCantidad);
        etLlantasMedida = findViewById(R.id.editTextLlantasMedida);

        for (int i = 0; i < spinnerEstado.length; i++) {
            spinnerEstado[i].setAdapter(adapter);
        }

        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validar formulario
                CopyDataToDataModel();
                if (interiorData.Validar()){
                    Toast.makeText(InteriorActivity.this, "Validacion Correcta", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InteriorActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("interior", interiorData);
                    intent.putExtras(bundle);
                    setResult(Activity.RESULT_OK, intent);
                    finish();

                }else{
                    Toast.makeText(InteriorActivity.this, "Validacion Incorrecta Debe Llenar Todos Los Campos", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void setSpinner(){
        spinnerEstado[0] = (Spinner) findViewById(R.id.spinnerInt1);
        spinnerEstado[1] = (Spinner) findViewById(R.id.spinnerInt2);
        spinnerEstado[2] = (Spinner) findViewById(R.id.spinnerInt3);
        spinnerEstado[3] = (Spinner) findViewById(R.id.spinnerInt4);
        spinnerEstado[4] = (Spinner) findViewById(R.id.spinnerInt5);
        spinnerEstado[5] = (Spinner) findViewById(R.id.spinnerInt6);
        spinnerEstado[6] = (Spinner) findViewById(R.id.spinnerInt7);
        spinnerEstado[7] = (Spinner) findViewById(R.id.spinnerInt8);
        spinnerEstado[8] = (Spinner) findViewById(R.id.spinnerInt9);
        spinnerEstado[9] = (Spinner) findViewById(R.id.spinnerInt10);
        spinnerEstado[10] = (Spinner) findViewById(R.id.spinnerInt11);
        spinnerEstado[11] = (Spinner) findViewById(R.id.spinnerInt12);
        spinnerEstado[12] = (Spinner) findViewById(R.id.spinnerInt13);
        spinnerEstado[13] = (Spinner) findViewById(R.id.spinnerInt14);
        spinnerEstado[14] = (Spinner) findViewById(R.id.spinnerInt15);
        spinnerEstado[15] = (Spinner) findViewById(R.id.spinnerInt16);
        spinnerEstado[16] = (Spinner) findViewById(R.id.spinnerInt17);
    }

    private void CopyDataToDataModel(){
        String[] s = new String[spinnerEstado.length];
        for (int i = 0; i < spinnerEstado.length; i++) {
            s[i] = spinnerEstado[i].getSelectedItem().toString();
        }

        interiorData.setData(s);
        interiorData.setLlantasMarca(etLlantasMarca.getText().toString());
        interiorData.setLlantasCantidad(etLlantasCantidad.getText().toString());
        interiorData.setLlantasMedida(etLlantasMedida.getText().toString());

    }
}
