package com.example.mrd.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mrd.DataModel.ClienteData;
import com.example.mrd.DataModel.ExteriorData;
import com.example.mrd.DataModel.InteriorData;
import com.example.mrd.DataModel.MotorData;
import com.example.mrd.R;

public class CapturaActivity extends AppCompatActivity {

    static final int CODE_CLIENTE = 1;
    static final int CODE_EXTERIOR = 2;
    static final int CODE_INTERIOR = 3;
    static final int CODE_MOTOR = 4;

    ClienteData clienteData = null;
    ExteriorData exteriorData =null;
    InteriorData interiorData =null;
    MotorData motorData = null;

    private Button btnCliente;
    private Button btnExterior;
    private Button btnInterior;
    private Button btnMotor;

    private ImageView imgClienteOK;
    private ImageView imgExteriorOK;
    private ImageView imgInteriorOK;
    private ImageView imgMotorOK;

    private ImageButton  ibClienteEdit;
    private ImageButton  ibExteriorEdit;
    private ImageButton  ibInteriorEdit;
    private ImageButton  ibMotorEdit;




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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura);

        btnCliente = findViewById(R.id.buttonCliente);
        btnExterior = findViewById(R.id.buttonExterior);
        btnInterior = findViewById(R.id.buttonInterior);
        btnMotor = findViewById(R.id.buttonMotor);
        imgClienteOK = findViewById(R.id.imageViewClienteOK);
        ibClienteEdit = findViewById(R.id.imageButtonClienteEdit);

        imgClienteOK = findViewById(R.id.imageViewClienteOK);
        imgExteriorOK= findViewById(R.id.imageViewExteriorOK);
        imgInteriorOK = findViewById(R.id.imageViewInteriorOK);
        imgMotorOK = findViewById(R.id.imageViewMotorOK);

        ibClienteEdit = findViewById(R.id.imageButtonClienteEdit);
        ibExteriorEdit = findViewById(R.id.imageButtonExteriorEdit);
        ibInteriorEdit = findViewById(R.id.imageButtonInteriorEdit);
        ibMotorEdit = findViewById(R.id.imageButtonMotorEdit);


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
}
