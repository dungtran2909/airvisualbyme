package com.hfad.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DataState implements Serializable {
    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private List<State> states = null;

    public DataState() {
    }

    public DataState(String status, List<State> states) {
        this.status = status;
        this.states = states;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

}
