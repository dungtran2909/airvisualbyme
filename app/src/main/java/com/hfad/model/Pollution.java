package com.hfad.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pollution implements Serializable {
    @SerializedName("aqius")
    private int aqius;

    public Pollution() {
    }

    public Pollution(int aqius) {
        this.aqius = aqius;
    }

    public int getAqius() {
        return aqius;
    }

    public void setAqius(int aqius) {
        this.aqius = aqius;
    }
}
