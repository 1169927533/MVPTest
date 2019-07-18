package com.example.mvptest.mvp.picfra.model;

import android.os.Handler;
import android.os.Message;

import com.example.mvptest.mvp.newsfra.presenter.NewsPresenter;
import com.example.mvptest.net.Net;
import com.example.mvptest.object.PictureObj;
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

public class PicturemodelDaoImp  {
    Gson gson = new Gson();


    static List<PictureObj> pictureObjslist;//新闻数据

    MyHandler myHandler = new MyHandler(this);
    PicturemodelDao picturemodelDao;

    public void getPictureList(int start_num, int end_num,PicturemodelDao picturemodelDao) {
        this.picturemodelDao = picturemodelDao;
        pictureObjslist = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Net.picture)
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        final retrofit2.Call<ResponseBody> call = apiServer.getImgs(start_num, end_num);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String content = response.body().string();
                    JSONObject jsonObject = new JSONObject(content);
                    if (jsonObject.getString("code").equals("200")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("result");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            PictureObj newsObj = gson.fromJson(jsonArray.get(i).toString(), PictureObj.class);
                            pictureObjslist.add(newsObj);
                        }
                        myHandler.sendEmptyMessage(2);
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
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final Object activity = mWeakReference.get();
            if(activity != null){
                if(msg.what == 2){
                    picturemodelDao.showpicture(pictureObjslist);
                }
            }
        }
    }
}
