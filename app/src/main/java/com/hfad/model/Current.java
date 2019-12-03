package com.hfad.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Current implements Serializable {
    @SerializedName("weather")
    private Weather weather;

    @SerializedName("pollution")
    private Pollution pollution;

    public Current() {
    }

    public Current(Weather weather, Pollution pollution) {
        this.weather = weather;
        this.pollution = pollution;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Pollution getPollution() {
        return pollution;
    }

    public void setPollution(Pollution pollution) {
        this.pollution = pollution;
    }
}
