package com.example.mvptest.retrofit_study.dao;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServer {
    @GET("getWangYiNews/")
    Call<ResponseBody> getNews(@Query("page") int page, @Query("count") int count);


    @GET("getImages/")
    Call<ResponseBody> getImgs(@Query("page") int page, @Query("count") int count);

    @GET("weatherApi")
    Call<ResponseBody> getWeather(@Query("city") String city);

}
