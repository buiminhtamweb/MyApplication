package com.example.myapplication.Retrofit;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface APILink {
    

    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadFile(
            @HeaderMap Map<String, String> headers,
            @Part("content") RequestBody description,
            @Part MultipartBody.Part file
    );


    @Streaming
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);


}
