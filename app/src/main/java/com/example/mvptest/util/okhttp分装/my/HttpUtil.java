package com.example.mvptest.util.okhttp分装.my;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.mvptest.dagger.component.DaggerHttpUtilComponent;
import com.example.mvptest.dagger.model.NewsModelDaoImpMoudle;
import com.example.mvptest.object.Weather;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.PriorityQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    String content;

    public Comback combacsk;

    OkHttpClient okHttpClient;

    MyHandler handler;

    @Inject
    public HttpUtil(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        handler  = new MyHandler(this);
    }

    /*
    异步的get请求
     */
    public void get(String url, final Comback comback) {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                content = response.body().string();
                combacsk = comback;
                handler.sendEmptyMessage(1);
            }
        });
    }
    /*
    异步的post请求
     */
    public void post(String url, RequestBody requestBody,Comback comback){
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        builder.post(requestBody);
        Request request1 = builder.build();
        okHttpClient.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }




    class MyHandler extends Handler {
        WeakReference<Object> mWeakReference;

        public MyHandler(Object activity) {
            mWeakReference = new WeakReference<Object>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                combacsk.success_back(content);
            }
        }
    }

}
