package com.example.mrd.Network;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Clase usada para regresar el objeto de Retrofit
public class NetworkClient {
    private static final String BASE_URL = "http://192.168.100.112/mrd/public/api/";
    private static Retrofit retrofit;
    public static Retrofit getRetrofitClient(Context context) {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
