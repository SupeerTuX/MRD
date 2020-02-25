package com.example.mrd.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mrd.DataModel.ClienteData;
import com.example.mrd.DataModel.ExteriorData;
import com.example.mrd.DataModel.FormularioModel;
import com.example.mrd.DataModel.InteriorData;
import com.example.mrd.DataModel.MotorData;
import com.example.mrd.R;

import org.w3c.dom.Text;

import java.text.Normalizer;
import java.util.ArrayList;
import com.example.mrd.Activities.CapturaActivity;
import com.example.mrd.Utilidades.StringUtils;

public class TicketActivity extends AppCompatActivity {

    private static final int LIMITE_CARACTERES_POR_LINEA = 47;

    private static final int DEFENSA_DELANTERA = 26;
    private static final int CARROCERIA = 27;
    private static final int PARRILLA = 28;
    private static final int FAROS = 29;
    private static final int COFRE = 30;
    private static final int PARABRISAS = 31;
    private static final int LIMPIADORES = 32;
    private static final int EMBLEMAS = 33;
    private static final int PORTEZUELA_IZQ = 34;
    private static final int CRISTAL_IZQ = 35;
    private static final int MEDALLON = 36;
    private static final int CAJUELA = 37;
    private static final int DEFENSA_TRASERA = 38;
    private static final int PORTEZUELA_DER = 39;
    private static final int CRISTAL_DER = 40;
    private static final int ANTENAS = 41;
    private static final int ESPEJOS = 42;
    private static final int TAPONES_RUEDAS = 43;
    private static final int TAPON_GASOLINA = 44;
    private static final int SALPICADERA_DER = 45;
    private static final int SALPICADERA_IZQ = 46;


    private static final int TABLERO = 50;
    private static final int SIM_CARD = 66;

    private static final int MOTOR_INICIO = 69;
    private static final int MOTOR_FIN = 80;



    private TextView  genericRow;
    ArrayList<TextView> rows;
    ArrayList<String> ticketStr;

    private ClienteData formularioData;
    private ExteriorData exteriorData;
    private InteriorData interiorData;
    private MotorData motorData;

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(TicketActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("ticketFormat", ticketStr);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        Bundle obj = getIntent().getExtras();
        formularioData = (ClienteData) obj.getSerializable("ticket");
        exteriorData = (ExteriorData) obj.getSerializable("exterior");
        interiorData = (InteriorData) obj.getSerializable("interior");
        motorData = (MotorData) obj.getSerializable("motor");

        rows = new ArrayList<TextView>();
        ticketStr = new ArrayList<String>();
         //Llenamos los campos del ticket

        //Header 1
        genericRow = findViewById(R.id.textViewTicket1);
        rows.add(genericRow);
        //Header 2
        genericRow = findViewById(R.id.textViewTicket2);
        rows.add(genericRow);
        //Header 3
        genericRow = findViewById(R.id.textViewTicket3);
        rows.add(genericRow);
        //Folio
        genericRow = findViewById(R.id.textViewTicket4);
        rows.add(genericRow);
        //Fecha y Hora
        genericRow = findViewById(R.id.textViewTicket5);
        rows.add(genericRow);
        //Header Direccion
        genericRow = findViewById(R.id.textViewTicket6);
        rows.add(genericRow);
        //Direccion
        genericRow = findViewById(R.id.textViewTicket7);
        rows.add(genericRow);
        //Header Motivo Inventario
        genericRow = findViewById(R.id.textViewTicket8);
        rows.add(genericRow);
        //Motivo Inventario L1
        genericRow = findViewById(R.id.textViewTicket9);
        rows.add(genericRow);
        //Motivo Inventario L2
        genericRow = findViewById(R.id.textViewTicket10);
        rows.add(genericRow);
        //Motivo Inventario L3
        genericRow = findViewById(R.id.textViewTicket11);
        rows.add(genericRow);
        //Motivo Inventario L4
        genericRow = findViewById(R.id.textViewTicket12);
        rows.add(genericRow);
        //Separador
        genericRow = findViewById(R.id.textViewTicket13);
        rows.add(genericRow);
        //Vehiculo marca / Tipo
        genericRow = findViewById(R.id.textViewTicket14);
        rows.add(genericRow);
        //Modelo / Color
        genericRow = findViewById(R.id.textViewTicket15);
        rows.add(genericRow);
        //Placas / No Serie
        genericRow = findViewById(R.id.textViewTicket16);
        rows.add(genericRow);
        //Conductor del vehiculo
        genericRow = findViewById(R.id.textViewTicket17);
        rows.add(genericRow);
        //Conductor del vehiculo linea
        genericRow = findViewById(R.id.textViewTicket18);
        rows.add(genericRow);
        //LLaves /  Telefono
        genericRow = findViewById(R.id.textViewTicket19);
        rows.add(genericRow);
        //Gruas / Clave operador
        genericRow = findViewById(R.id.textViewTicket20);
        rows.add(genericRow);
        //Autoridad o empresa
        genericRow = findViewById(R.id.textViewTicket21);
        rows.add(genericRow);
        //Autoridad o empresa linea
        genericRow = findViewById(R.id.textViewTicket22);
        rows.add(genericRow);
        //Separador
        genericRow = findViewById(R.id.textViewTicket23);
        rows.add(genericRow);
        //Header estado
        genericRow = findViewById(R.id.textViewTicket24);
        rows.add(genericRow);
        //Separador
        genericRow = findViewById(R.id.textViewTicket25);
        rows.add(genericRow);
        //Header Exterior
        genericRow = findViewById(R.id.textViewTicket26);
        rows.add(genericRow);
        //Defensa
        genericRow = findViewById(R.id.textViewTicket27);
        rows.add(genericRow);
        //Carroceria
        genericRow = findViewById(R.id.textViewTicket28);
        rows.add(genericRow);
        //Parrilla
        genericRow = findViewById(R.id.textViewTicket29);
        rows.add(genericRow);
        //Faros
        genericRow = findViewById(R.id.textViewTicket30);
        rows.add(genericRow);
        //Cofre
        genericRow = findViewById(R.id.textViewTicket31);
        rows.add(genericRow);
        //Parabrisas
        genericRow = findViewById(R.id.textViewTicket32);
        rows.add(genericRow);
        //Limpiadores
        genericRow = findViewById(R.id.textViewTicket33);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket34);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket35);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket36);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket37);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket38);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket39);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket40);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket41);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket42);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket43);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket44);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket45);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket46);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket47);
        rows.add(genericRow);
        //Separadpr Interior
        genericRow = findViewById(R.id.textViewTicket48);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket49);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket50);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket51);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket52);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket53);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket54);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket55);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket56);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket57);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket58);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket59);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket60);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket61);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket62);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket63);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket64);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket65);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket66);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket67);
        rows.add(genericRow);
        //Separador Motor
        genericRow = findViewById(R.id.textViewTicket68);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket69);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket70);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket71);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket72);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket73);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket74);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket75);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket76);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket77);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket78);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket79);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket80);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket81);
        rows.add(genericRow);
        //Separador llantas
        genericRow = findViewById(R.id.textViewTicket82);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket83);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket84);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket85);
        rows.add(genericRow);
        //Separador tanque de gasolina
        genericRow = findViewById(R.id.textViewTicket86);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket87);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket88);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket89);
        rows.add(genericRow);
        //Separador firmas
        genericRow = findViewById(R.id.textViewTicket90);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket91);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket92);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket93);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket94);
        rows.add(genericRow);
        //Carga consiste
        genericRow = findViewById(R.id.textViewTicket95);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket96);
        rows.add(genericRow);
        //Observaciones
        genericRow = findViewById(R.id.textViewTicket97);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket98);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket99);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket100);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket101);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket102);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket103);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket104);
        rows.add(genericRow);
        //Firma 1
        genericRow = findViewById(R.id.textViewTicket105);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket106);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket107);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket108);
        rows.add(genericRow);
        //Firma 2
        genericRow = findViewById(R.id.textViewTicket109);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket110);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket111);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket112);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket113);
        rows.add(genericRow);
        //
        genericRow = findViewById(R.id.textViewTicket114);
        rows.add(genericRow);







        //Set Folio
        rows.get(3).append(formularioData.getFolio());
        //Fecha y hora
        rows.get(4).append(formularioData.getDate()); rows.get(4).append("  ");
        rows.get(4).append(getString(R.string.ticket_fecha)); rows.get(4).append(formularioData.getTime());
        //Header direccion
        //Direccion
        rows.get(6).setText(formularioData.getDireccion());
        //Header Motivo de inventario
        //Motivo Inventario L1
        rows.get(8).setText(getResources().getString(R.string.ticket_motivo_asistencia));
        if(getResources().getString(R.string.ticket_motivo_asistencia).equals(formularioData.getMotivoInventario()))
            rows.get(8).append(getResources().getString(R.string.ticket_selected));
        else rows.get(8).append(getResources().getString(R.string.ticket_unselected));

        rows.get(8).append("    ");

        rows.get(8).append(getResources().getString(R.string.ticket_motivo_siniestro));
        if(getResources().getString(R.string.ticket_motivo_siniestro).equals(formularioData.getMotivoInventario()))
            rows.get(8).append(getResources().getString(R.string.ticket_selected));
        else rows.get(8).append(getResources().getString(R.string.ticket_unselected));

        //Motivo Inventario L2
        rows.get(9).setText(getResources().getString(R.string.ticket_motivo_alcoholimetro));
        if(getResources().getString(R.string.ticket_motivo_alcoholimetro).equals(formularioData.getMotivoInventario()))
            rows.get(8).append(getResources().getString(R.string.ticket_selected));
        else rows.get(8).append(getResources().getString(R.string.ticket_unselected));

        rows.get(9).append("    ");

        rows.get(9).append(getResources().getString(R.string.ticket_motivo_mal_estacionado));
        if(getResources().getString(R.string.ticket_motivo_mal_estacionado).equals(formularioData.getMotivoInventario()))
            rows.get(9).append(getResources().getString(R.string.ticket_selected));
        else rows.get(9).append(getResources().getString(R.string.ticket_unselected));

        //Motivo Inventario L3
        rows.get(10).setText(getResources().getString(R.string.ticket_motivo_detencion));
        if(getResources().getString(R.string.ticket_motivo_detencion).equals(formularioData.getMotivoInventario()))
            rows.get(10).append(getResources().getString(R.string.ticket_selected));
        else rows.get(10).append(getResources().getString(R.string.ticket_unselected));

        rows.get(10).append("    ");

        rows.get(10).append(getResources().getString(R.string.ticket_motivo_solo_inventario));
        if(getResources().getString(R.string.ticket_motivo_solo_inventario).equals(formularioData.getMotivoInventario()))
            rows.get(10).append(getResources().getString(R.string.ticket_selected));
        else rows.get(10).append(getResources().getString(R.string.ticket_unselected));

        //Motivo Inventario L4
        rows.get(11).setText(getResources().getString(R.string.ticket_motivo_operativo_especial));
        if(getResources().getString(R.string.ticket_motivo_operativo_especial).equals(formularioData.getMotivoInventario()))
            rows.get(11).append(getResources().getString(R.string.ticket_selected));
        else rows.get(11).append(getResources().getString(R.string.ticket_unselected));

        //Separador
        //Vehiculo marca / tipo
        rows.get(13).append(formularioData.getVehiculoMarca()); rows.get(13).append("  ");
        rows.get(13).append(getResources().getString(R.string.ticket_vehiculo_tipo));
        rows.get(13).append(formularioData.getVehiculoTipo());

        //Vehiculo Modelo / Color
        rows.get(14).append(formularioData.getVehiculoModelo()); rows.get(14).append("  ");
        rows.get(14).append(getResources().getString(R.string.ticket_vehiculo_color));
        rows.get(14).append(formularioData.getVehiculoColor());

        //Vehiculo Placas / No serie
        rows.get(15).append(formularioData.getVehiculoPlacas()); rows.get(15).append("  ");
        rows.get(15).append(getResources().getString(R.string.ticket_vehiculo_serie));
        rows.get(15).append(formularioData.getVehiculoSerie());

        //Conductor del vehiculo literal

        //Conductor de vehiculo
        rows.get(17).setText(formularioData.getVehiculoPropietario());

        //llaves / telefono
        rows.get(18).append(formularioData.getVehiculoLlaves()); rows.get(18).append("  ");
        rows.get(18).append(getResources().getString(R.string.ticket_vehiculo_tel));
        rows.get(18).append(formularioData.getTelefono());

        //Grua / Clave operador
        rows.get(19).append(formularioData.getOperadorGrua()); rows.get(19).append("  ");
        rows.get(19).append(getResources().getString(R.string.ticket_vehiculo_clave));
        rows.get(19).append(formularioData.getOperadorClave());

        //Autoridad o empresa solicidante
        rows.get(21).setText(formularioData.getAutoridad());

        //Formulario Exterior
        ticketExterior();
        //Formulario Exterior
        ticketInterior();
        //Formulario Motor
        ticketMotor();
        //FormularioFooter
        ticketFooter();


        Log.d("DEBUG", "Ticket");
        for (int i = 0; i < rows.size(); i++){
            Log.d("DEBUG", StringUtils.center(rows.get(i).getText().toString(), 47) );
        }

        copyTicket();
/*
        Log.d("DEBUG", "Ticket");
        for (int i = 0; i < rows.size(); i++){
            Log.d("DEBUG", StringUtils.center(ticketStr.get(i), 47) );
        }
*/


    }

    private void ticketExterior(){

        String[] str = exteriorData.getData();
        int separador;
        int bienLen =  28;
        int malLen =  33;
        int noTraeLen =  40;
        int strIndex  = 0;

        for (int index = DEFENSA_DELANTERA; index <= SALPICADERA_IZQ; index++ ){

            if(str[strIndex].equals(getResources().getString(R.string.ticket_bien))){
                //Separadores a agregar
                separador = bienLen - rows.get(index).getText().toString().length();
                //Agregando separadores
                for (int i = 0; i < separador; i++){
                    rows.get(index).append("-");
                }
                //Agregado el seleccionado
                rows.get(index).append(getResources().getString(R.string.ticket_selected));
                rows.get(index).append("----------------");

            }else if(str[strIndex].equals("Mal")){
                if(str[strIndex].equals(getResources().getString(R.string.ticket_mal))) {
                    //Separadores a agregar
                    separador = malLen - rows.get(index).getText().toString().length();
                    //Agregando separadores
                    for (int i = 0; i < separador; i++) {
                        rows.get(index).append("-");
                    }
                    //Agregado el seleccionado
                    rows.get(index).append(getResources().getString(R.string.ticket_selected));
                    rows.get(index).append("-----------");
                }
            }else if(str[strIndex].equals("No Trae")){
                if(str[strIndex].equals(getResources().getString(R.string.ticket_no_trae))) {
                    //Separadores a agregar
                    separador = noTraeLen - rows.get(index).getText().toString().length();
                    //Agregando separadores
                    for (int i = 0; i < separador; i++) {
                        rows.get(index).append("-");
                    }
                    //Agregado el seleccionado
                    rows.get(index).append(getResources().getString(R.string.ticket_selected));
                    rows.get(index).append("----");
                }
            }
            strIndex++;
        }
    }

    private void ticketInterior(){
        String[] str = interiorData.getData();
        int separador;
        int bienLen =  28;
        int malLen =  33;
        int noTraeLen =  40;
        int strIndex  = 0;

        for (int index = TABLERO; index <= SIM_CARD; index++ ){

            if(str[strIndex].equals(getResources().getString(R.string.ticket_bien))){
                //Separadores a agregar
                separador = bienLen - rows.get(index).getText().toString().length();
                //Agregando separadores
                for (int i = 0; i < separador; i++){
                    rows.get(index).append("-");
                }
                //Agregado el seleccionado
                rows.get(index).append(getResources().getString(R.string.ticket_selected));
                rows.get(index).append("----------------");

            }else if(str[strIndex].equals("Mal")){
                if(str[strIndex].equals(getResources().getString(R.string.ticket_mal))) {
                    //Separadores a agregar
                    separador = malLen - rows.get(index).getText().toString().length();
                    //Agregando separadores
                    for (int i = 0; i < separador; i++) {
                        rows.get(index).append("-");
                    }
                    //Agregado el seleccionado
                    rows.get(index).append(getResources().getString(R.string.ticket_selected));
                    rows.get(index).append("-----------");
                }
            }else if(str[strIndex].equals("No Trae")){
                if(str[strIndex].equals(getResources().getString(R.string.ticket_no_trae))) {
                    //Separadores a agregar
                    separador = noTraeLen - rows.get(index).getText().toString().length();
                    //Agregando separadores
                    for (int i = 0; i < separador; i++) {
                        rows.get(index).append("-");
                    }
                    //Agregado el seleccionado
                    rows.get(index).append(getResources().getString(R.string.ticket_selected));
                    rows.get(index).append("----");
                }
            }
            strIndex++;
        }
    }

    private void ticketMotor(){
        String[] str = motorData.getData();
        int separador;
        int bienLen =  28;
        int malLen =  33;
        int noTraeLen =  40;
        int strIndex  = 0;

        for (int index = MOTOR_INICIO; index < MOTOR_FIN; index++ ){

            if(str[strIndex].equals(getResources().getString(R.string.ticket_bien))){
                //Separadores a agregar
                separador = bienLen - rows.get(index).getText().toString().length();
                //Agregando separadores
                for (int i = 0; i < separador; i++){
                    rows.get(index).append("-");
                }
                //Agregado el seleccionado
                rows.get(index).append(getResources().getString(R.string.ticket_selected));
                rows.get(index).append("----------------");

            }else if(str[strIndex].equals("Mal")){
                if(str[strIndex].equals(getResources().getString(R.string.ticket_mal))) {
                    //Separadores a agregar
                    separador = malLen - rows.get(index).getText().toString().length();
                    //Agregando separadores
                    for (int i = 0; i < separador; i++) {
                        rows.get(index).append("-");
                    }
                    //Agregado el seleccionado
                    rows.get(index).append(getResources().getString(R.string.ticket_selected));
                    rows.get(index).append("-----------");
                }
            }else if(str[strIndex].equals("No Trae")){
                if(str[strIndex].equals(getResources().getString(R.string.ticket_no_trae))) {
                    //Separadores a agregar
                    separador = noTraeLen - rows.get(index).getText().toString().length();
                    //Agregando separadores
                    for (int i = 0; i < separador; i++) {
                        rows.get(index).append("-");
                    }
                    //Agregado el seleccionado
                    rows.get(index).append(getResources().getString(R.string.ticket_selected));
                    rows.get(index).append("----");
                }
            }
            strIndex++;
        }
    }

    private void ticketFooter(){
        //llantas

        rows.get(83).append(interiorData.getLlantasMarca());
        rows.get(83).append("   ");
        rows.get(83).append(getResources().getString(R.string.ticket_int_llantas_medida));
        rows.get(83).append(interiorData.getLlantasMedida());

        rows.get(84).append(interiorData.getLlantasCantidad());

        //tanque de gasolina
        // |--|--|--|--|
        rows.get(87).setText("E |--|--|--|--| F");

        //carga

        //Solo debe ejecutarse si la cadena supera el espacio maximo
        if (motorData.getMotorCarga().length() + getResources().getString(R.string.ticket_motor_footer5).length() > LIMITE_CARACTERES_POR_LINEA ){
            String sub = motorData.getMotorCarga().substring(0, getResources().getString(R.string.ticket_motor_footer5).length());
            int espacio = sub.lastIndexOf(" ");
            int strLen  = motorData.getMotorCarga().length();
            String str1 = motorData.getMotorCarga().substring(0, espacio);
            String str2 = motorData.getMotorCarga().substring(++espacio, strLen);
            rows.get(94).append(str1);
            rows.get(95).setText(str2);
        }
        //TODO establecer else para guardar el texto en caso de que no supere el limite de la linea

        //observaciones
        if (motorData.getMotorObservaciones().length() + getResources().getString(R.string.ticket_motor_footer7).length() > LIMITE_CARACTERES_POR_LINEA ){
            String sub = motorData.getMotorObservaciones().substring(0, getResources().getString(R.string.ticket_motor_footer7).length());
            int espacio = sub.lastIndexOf(" ");
            int strLen  = motorData.getMotorObservaciones().length();
            String str1 = motorData.getMotorObservaciones().substring(0, espacio);
            String str2 = motorData.getMotorObservaciones().substring(++espacio, strLen);
            rows.get(96).append(str1);
            rows.get(97).setText(str2);
        }

    }


    //Copia el texto de los textview a un array string
    private void copyTicket(){

        for (int i = 0; i < rows.size(); i++){
            ticketStr.add(rows.get(i).getText().toString());

            if (rows.get(i).getText().toString().equals("_")){
                ticketStr.add(" ");
                ticketStr.add(" ");
                ticketStr.add(" ");
                ticketStr.add(" ");
            }
        }

    }


}


