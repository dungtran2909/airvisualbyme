package com.hfad.network;


import com.hfad.model.Data;
import com.hfad.model.DataCity;
import com.hfad.model.DataMyLocal;
import com.hfad.model.DataState;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {
    @GET("countries")
    Call<Data> getCountry(@Query("key") String key);

    @GET("nearest_city")
    Call<DataMyLocal> getMyLocal(@Query("lat") String lat,
                                 @Query("lon") String lon,
                                 @Query("key") String key);

    @GET("states")
    Call<DataState> getState(@Query("country") String country,
                             @Query("key") String key);

    @GET("cities")
    Call<DataCity> getCity(@Query("state") String state,
                           @Query("country") String country,
                           @Query("key") String key);
    @GET("city")
    Call<DataMyLocal> getDetailsCity(@Query("city") String city,
                                     @Query("state") String state,
                                     @Query("country") String country,
                                     @Query("key") String key);
}