package com.example.mrd.DataModel;


import com.example.mrd.R;

import java.io.Serializable;

public class FormularioModel implements Serializable {
    ClienteData clienteData;
    String[] data;

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public ClienteData getClienteData() {
        return clienteData;
    }

    public void setClienteData(ClienteData clienteData) {
        this.clienteData = clienteData;
    }

    public FormularioModel(ClienteData clienteData, String[] data) {
        this.clienteData = clienteData;
        this.data = data;
    }


    public void FormatearTexto(){

    }

}
