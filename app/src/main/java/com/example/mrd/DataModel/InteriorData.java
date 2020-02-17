package com.example.mrd.DataModel;

import java.io.Serializable;

public class InteriorData implements Serializable {

    private String[] data = new String[17];
    private String llantasMarca;
    private String llantasCantidad;
    private String llantasMedida;

    public InteriorData() {
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public String getLlantasMarca() {
        return llantasMarca;
    }

    public void setLlantasMarca(String llantasMarca) {
        this.llantasMarca = llantasMarca;
    }

    public String getLlantasCantidad() {
        return llantasCantidad;
    }

    public void setLlantasCantidad(String llantasCantidad) {
        this.llantasCantidad = llantasCantidad;
    }

    public String getLlantasMedida() {
        return llantasMedida;
    }

    public void setLlantasMedida(String llantasMedida) {
        this.llantasMedida = llantasMedida;
    }

    public boolean Validar(){

        if (this.getLlantasMarca() == null || this.getLlantasMarca().isEmpty() ) return false;
        else if (this.getLlantasCantidad() == null || this.getLlantasCantidad().isEmpty() ) return false;
        else if (this.getLlantasMedida() == null || this.getLlantasMedida().isEmpty() ) return false;
        else return true;
    }
}
