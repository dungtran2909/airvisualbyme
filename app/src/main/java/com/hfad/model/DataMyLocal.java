package com.hfad.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataMyLocal implements Serializable {
    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private MyLocal myLocal;

    public DataMyLocal() {
    }

    public DataMyLocal(String status, MyLocal myLocal) {
        this.status = status;
        this.myLocal = myLocal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MyLocal getMyLocal() {
        return myLocal;
    }

    public void setMyLocal(MyLocal myLocal) {
        this.myLocal = myLocal;
    }
}
