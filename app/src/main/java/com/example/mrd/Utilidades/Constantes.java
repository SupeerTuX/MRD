package com.example.mrd.Utilidades;

import android.os.Environment;

import java.io.File;

public class Constantes {
    public static void crearRutaCarpetaImg(){
        File dir = new File(Environment.getExternalStorageDirectory().toString()+"/Pictures/ImagenesImprimir");

        if(!dir.exists()){
            dir.mkdir();
        }
    }

    public static File getRutaDestinoImg(String nombreImg){
        return new File(Environment.getExternalStorageDirectory().toString()+"/Pictures/ImagenesImprimir/"+nombreImg+".png");
    }
}
