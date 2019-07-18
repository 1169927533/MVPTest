package com.example.mvptest.mvp.newsfra.model;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.mvptest.mvp.newsfra.presenter.NewsPresenter;
import com.example.mvptest.net.Net;
import com.example.mvptest.object.NewsObj;
import com.example.mvptest.object.Weather;
import com.example.mvptest.retrofit_study.dao.ApiServer;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsmodelDaoImp {

    Gson gson = new Gson();


    static List<NewsObj> newsObjslist;//新闻数据

    NewsmodelDao newsPresenter;

    public NewsmodelDaoImp(NewsPresenter newsPresenter) {
        this.newsPresenter = newsPresenter;
    }

    MyHandler myHandler = new MyHandler(this);

    public void getNews(int start_num, int end_num) {
        newsObjslist = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Net.news_Netease)
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        final retrofit2.Call<ResponseBody> call = apiServer.getNews(start_num, end_num);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String content = response.body().string();
                    JSONObject jsonObject = new JSONObject(content);
                    if (jsonObject.getString("code").equals("200")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("result");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            NewsObj newsObj = gson.fromJson(jsonArray.get(i).toString(), NewsObj.class);
                            newsObjslist.add(newsObj);
                        }

                        myHandler.sendEmptyMessage(1);
                    }
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getWeather(String city) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Net.weather_forecast)
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        final retrofit2.Call<ResponseBody> call = apiServer.getWeather(city);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String content = response.body().string();
                    JSONObject jsonObject = new JSONObject(content);
                    if (jsonObject.getString("code").equals("200")) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        String city = jsonObject1.getString("city");
                        String api = jsonObject1.getString("aqi");
                        if(jsonObject1.getString("aqi") == "null" || jsonObject1.getString("aqi").equals("null")) {
                            api = "暂无数据";
                        }
                        JSONObject jsonObject2 = jsonObject1.getJSONArray("forecast").getJSONObject(3);
                        String maxt = jsonObject2.getString("high");
                        String mint = jsonObject2.getString("low");
                        String type = jsonObject2.getString("type");
                        Weather weather = new Weather(city,api,maxt,mint,type);
                        Message msg = Message.obtain();
                        msg.what = 3;
                        Bundle b = new Bundle();
                        b.putParcelable("MyObject", (Parcelable) weather);
                        msg.setData(b);
                        myHandler.sendMessage(msg);
                    }
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    class MyHandler extends Handler {
        WeakReference<Object> mWeakReference;
        public MyHandler(Object activity){
            mWeakReference = new WeakReference<Object>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            final Object activity = mWeakReference.get();
            if(activity != null){
                if(msg.what == 1){
                    newsPresenter.getNews(newsObjslist);
                }else if(msg.what == 3){
                    Weather objectRcvd = (Weather) msg.getData().getParcelable("MyObject");
                    newsPresenter.getWeather(objectRcvd);
                }
            }
        }
    }
}
