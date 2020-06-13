package com.example.mrd.Network;

import com.example.mrd.DataModel.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetAPI {
    @GET("folio/{region}")
    Call<ResponseModel> getFolio(@Path("region") String region);
}