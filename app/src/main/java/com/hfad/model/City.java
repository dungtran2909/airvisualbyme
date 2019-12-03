package com.hfad.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class City implements Serializable {
    @SerializedName("city")
    private String nameCity;

    public City() {
    }

    public City(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }
}
