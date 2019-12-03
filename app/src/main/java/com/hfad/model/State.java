package com.hfad.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class State implements Serializable {
    @SerializedName("state")
    private String nameState;

    public State() {
    }

    public State(String nameState) {
        this.nameState = nameState;
    }

    public String getNameState() {
        return nameState;
    }

    public void setNameState(String nameState) {
        this.nameState = nameState;
    }
}
