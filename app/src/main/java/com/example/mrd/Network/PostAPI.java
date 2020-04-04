package com.example.mrd.Network;

import com.example.mrd.DataModel.ReportModel;
import com.example.mrd.DataModel.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostAPI {
    @POST("reporte")
    Call<ResponseModel> postReporte(@Body ReportModel post);
}
