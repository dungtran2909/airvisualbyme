package com.hfad.model;

public class CityInfo {
    private String nameState;
    private String nameCountry;
    private String nameCity;
    private int tp;
    private float hu;
    private float ws;
    private float ic;
    private int aqi;

    public CityInfo() {
    }

    public CityInfo(String nameState, String nameCountry, String nameCity, int tp, float hu, float ws, float ic, int aqi) {
        this.nameState = nameState;
        this.nameCountry = nameCountry;
        this.nameCity = nameCity;
        this.tp = tp;
        this.hu = hu;
        this.ws = ws;
        this.ic = ic;
        this.aqi = aqi;
    }

    public String getNameState() {
        return nameState;
    }

    public void setNameState(String nameState) {
        this.nameState = nameState;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
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

    public float getIc() {
        return ic;
    }

    public void setIc(float ic) {
        this.ic = ic;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }
}
