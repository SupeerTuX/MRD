package com.example.mrd.DataModel;

import java.io.Serializable;

public class ExteriorData implements Serializable {
    private String[] data = new String[21];

    public ExteriorData() {
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
