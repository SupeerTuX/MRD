package com.example.mrd.Network;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UploadAPI {
    @POST("multiupload/{folio}/{region}")
    Call<ResponseBody> uploadImage(@Body RequestBody files, @Path("folio") String folio, @Path("region") String region);
}
