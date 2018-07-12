package com.android.omdbapp.omdb.services;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {

    private static Retrofit retrofitclient;
    private static String BASE_URL = "http://www.omdbapi.com";


    public static Retrofit getRetrofitClient() {
        if (retrofitclient == null) {
            retrofitclient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitclient;
    }


}
