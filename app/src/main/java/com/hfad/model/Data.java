package com.hfad.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {
    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private List<Country> countries = null;


    public Data() {
    }

    public Data(String status, List<Country> countries) {
        this.status = status;
        this.countries = countries;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
