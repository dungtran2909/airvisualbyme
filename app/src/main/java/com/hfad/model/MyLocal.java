package com.hfad.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyLocal implements Serializable {
    @SerializedName("city")
    private String city;

    @SerializedName("state")
    private String state;

    @SerializedName("country")
    private String country;

    @SerializedName("current")
    private Current current;

    public MyLocal() {
    }

    public MyLocal(String city, String state, String country, Current current) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.current = current;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }
}
