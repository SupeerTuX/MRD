package com.example.mrd.DataModel;

import java.io.Serializable;

public class ClienteData implements Serializable {

    private String folio;
    private String date;
    private String time;
    private String direccion;
    private String motivoInventario;
    private String vehiculoMarca;
    private String vehiculoTipo;
    private String vehiculoModelo;
    private String vehiculoColor;
    private String vehiculoPlacas;
    private String vehiculoSerie;
    private String vehiculoPropietario;
    private boolean vehiculoLlaves;
    private String telefono;
    private String operadorGrua;
    private String operadorClave;
    private String autoridad;

    public ClienteData() {
    }


    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMotivoInventario() {
        return motivoInventario;
    }

    public void setMotivoInventario(String motivoInventario) {
        this.motivoInventario = motivoInventario;
    }

    public String getVehiculoMarca() {
        return vehiculoMarca;
    }

    public void setVehiculoMarca(String vehiculoMarca) {
        this.vehiculoMarca = vehiculoMarca;
    }

    public String getVehiculoTipo() {
        return vehiculoTipo;
    }

    public void setVehiculoTipo(String vehiculoTipo) {
        this.vehiculoTipo = vehiculoTipo;
    }

    public String getVehiculoModelo() {
        return vehiculoModelo;
    }

    public void setVehiculoModelo(String vehiculoModelo) {
        this.vehiculoModelo = vehiculoModelo;
    }

    public String getVehiculoColor() {
        return vehiculoColor;
    }

    public void setVehiculoColor(String vehiculoColor) {
        this.vehiculoColor = vehiculoColor;
    }

    public String getVehiculoPlacas() {
        return vehiculoPlacas;
    }

    public void setVehiculoPlacas(String vehiculoPlacas) {
        this.vehiculoPlacas = vehiculoPlacas;
    }

    public String getVehiculoSerie() {
        return vehiculoSerie;
    }

    public void setVehiculoSerie(String vehiculoSerie) {
        this.vehiculoSerie = vehiculoSerie;
    }

    public String getVehiculoPropietario() {
        return vehiculoPropietario;
    }

    public void setVehiculoPropietario(String vehiculoPropietario) {
        this.vehiculoPropietario = vehiculoPropietario;
    }

    public boolean isVehiculoLlaves() {
        return vehiculoLlaves;
    }

    public void setVehiculoLlaves(boolean vehiculoLlaves) {
        this.vehiculoLlaves = vehiculoLlaves;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String vehiculoTelefono) {
        this.telefono = vehiculoTelefono;
    }

    public String getOperadorGrua() {
        return operadorGrua;
    }

    public void setOperadorGrua(String operadorGrua) {
        this.operadorGrua = operadorGrua;
    }

    public String getOperadorClave() {
        return operadorClave;
    }

    public void setOperadorClave(String operadorClave) {
        this.operadorClave = operadorClave;
    }

    public String getAutoridad() {
        return autoridad;
    }

    public void setAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }


    //Metodo para verificar si alguna propiedad no ha sido asignada
    public boolean Validar(){
        if (this.getFolio() == null || this.getFolio().isEmpty() ) return false;
        else if (this.getDate() == null || this.getDate().isEmpty() ) return false;
        else if (this.getTime() == null || this.getTime().isEmpty()) return false;
        else if (this.getDireccion() == null || this.getDireccion().isEmpty()) return false;
        else if (this.getMotivoInventario() == null || this.getMotivoInventario().isEmpty()) return false;
        else if (this.getVehiculoMarca() == null || this.getVehiculoMarca().isEmpty()) return false;
        else if (this.getVehiculoTipo() == null || this.getVehiculoTipo().isEmpty()) return false;
        else if (this.getVehiculoModelo() == null || this.getVehiculoModelo().isEmpty()) return false;
        else if (this.getVehiculoColor() == null || this.getVehiculoColor().isEmpty()) return false;
        else if (this.getVehiculoPlacas() == null || this.getVehiculoPlacas().isEmpty()) return false;
        else if (this.getVehiculoSerie() == null || this.getVehiculoSerie().isEmpty()) return false;
        else if (this.getVehiculoPropietario() == null || this.getVehiculoPropietario().isEmpty()) return false;
        else if (this.getTelefono() == null || this.getTelefono().isEmpty()) return false;
        else if (this.getOperadorGrua() == null || this.getOperadorGrua().isEmpty()) return false;
        else if (this.getOperadorClave() == null || this.getOperadorClave().isEmpty()) return false;
        else if (this.getAutoridad() == null || this.getAutoridad().isEmpty()) return false;
        else return true;

    }

}
