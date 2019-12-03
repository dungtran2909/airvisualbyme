package com.hfad.network;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.hfad.model.Data;
import com.hfad.model.DataCity;
import com.hfad.model.DataMyLocal;
import com.hfad.model.DataState;

import javax.xml.parsers.FactoryConfigurationError;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static ApiService apiService;

    private Retrofit retrofit;

    private ApiService(String baseUrl) {
        initClient(baseUrl);
    }

    private void initClient(@NonNull String baseUrl) {
        if (TextUtils.isEmpty(baseUrl)) {
            return;
        }
        if (!baseUrl.endsWith("/")) {
            baseUrl = baseUrl + "/";
        }
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
    }

    public static void init(@NonNull String baseUrl) {
        if (apiService == null) {
            apiService = new ApiService(baseUrl);
        }
    }

    public static ApiService getInstance() {
        return apiService;
    }

    public void getCountry(String key, Callback<Data> callback) {
        if (retrofit != null) {
            Call<Data> getCountryApi = retrofit.create(RestApi.class).getCountry(key);
            getCountryApi.enqueue(callback);
        }
    }

    public void getMyLocal(String lat, String lon, String key, Callback<DataMyLocal> callback) {
        if (retrofit != null) {
            Call<DataMyLocal> getMyLocalApi = retrofit.create(RestApi.class).getMyLocal(lat, lon, key);
            getMyLocalApi.enqueue(callback);

        }
    }

    public void getState(String country, String key, Callback<DataState> callback) {
        if (retrofit != null) {
            Call<DataState> getStateApi = retrofit.create(RestApi.class).getState(country, key);
            getStateApi.enqueue(callback);
        }
    }

    public void getCity(String state, String country, String key, Callback<DataCity> callback) {
        if (retrofit != null) {
            Call<DataCity> getCityApi = retrofit.create(RestApi.class).getCity(state, country, key);
            getCityApi.enqueue(callback);
        }
    }

    public void getDetailsCity(String city, String state, String country, String key, Callback<DataMyLocal> callback) {
        if (retrofit != null) {
            Call<DataMyLocal> getDetailsCityApi = retrofit.create(RestApi.class).getDetailsCity(city, state, country, key);
            getDetailsCityApi.enqueue(callback);
        }
    }

}
