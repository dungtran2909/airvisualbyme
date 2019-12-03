package com.hfad.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Country implements Serializable {
    @SerializedName("country")
    private String nameCountry;

    public Country() {
    }

    public Country(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    @NonNull
    @Override
    public String toString() {
        return nameCountry;
    }
}
