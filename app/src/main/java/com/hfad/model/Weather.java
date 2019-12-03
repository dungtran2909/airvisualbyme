package com.hfad.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Weather implements Serializable {

    @SerializedName("tp")
    private int tp;

    @SerializedName("hu")
    private float hu;

    @SerializedName("ws")
    private float ws;

    @SerializedName("ic")
    private String ic;


    public Weather() {
    }

    public Weather(int tp, float hu, float ws, String ic) {
        this.tp = tp;
        this.hu = hu;
        this.ws = ws;
        this.ic = ic;
    }

    public int getTp() {
        return tp;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }

    public float getHu() {
        return hu;
    }

    public void setHu(float hu) {
        this.hu = hu;
    }

    public float getWs() {
        return ws;
    }

    public void setWs(float ws) {
        this.ws = ws;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }
}
