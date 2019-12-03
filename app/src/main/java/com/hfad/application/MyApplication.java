package com.hfad.application;

import android.app.Application;

import com.hfad.network.ApiService;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApiService.init("http://api.airvisual.com/v2/");
    }
}
