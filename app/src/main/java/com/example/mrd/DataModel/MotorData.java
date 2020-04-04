package com.example.mrd.DataModel;

import java.io.Serializable;

public class MotorData implements Serializable {

    private String[] data = new String[11];
    private String motorCarga;
    private String motorObservaciones;
    private String marcaBateria;
    private String medidorGasolina;

    public MotorData() {
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public String getMotorCarga() {
        return motorCarga;
    }

    public void setMotorCarga(String motorCarga) {
        this.motorCarga = motorCarga;
    }

    public String getMotorObservaciones() {
        return motorObservaciones;
    }

    public void setMotorObservaciones(String motorObservaciones) {
        this.motorObservaciones = motorObservaciones;
    }

    public String getMarcaBateria() {
        return marcaBateria;
    }

    public void setMarcaBateria(String marcaBateria) {
        this.marcaBateria = marcaBateria;
    }

    public String getMedidorGasolina() {
        return medidorGasolina;
    }

    public void setMedidorGasolina(String medidorGasolina) {
        this.medidorGasolina = medidorGasolina;
    }

    public boolean Validar(){
        if(this.getMotorCarga() == null || this.getMotorCarga().isEmpty()) return false;
        else if(this.getMotorObservaciones() == null || this.getMotorObservaciones().isEmpty()) return false;
        else if(this.getMarcaBateria() == null || this.getMarcaBateria().isEmpty()) return false;
        else if(this.getMedidorGasolina() == null || this.getMedidorGasolina().isEmpty() ) return false;
        else return true;

    }
}
