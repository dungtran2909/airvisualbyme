package com.hfad.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DataCity implements Serializable {
    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private List<City> cities = null;

    public DataCity() {
    }

    public DataCity(String status, List<City> cities) {
        this.status = status;
        this.cities = cities;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
