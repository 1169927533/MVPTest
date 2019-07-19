package com.example.mvptest.util.okhttp分装.my;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.mvptest.dagger.component.DaggerHttpUtilComponent;
import com.example.mvptest.dagger.model.NewsModelDaoImpMoudle;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {
    String content;

    public Comback combacsk;


    OkHttpClient okHttpClient;

    @Inject
    public HttpUtil(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }


    public void get(String url, final Comback comback) {

        /*if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder().build();
        }*/
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


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                combacsk.success_back(content);
            }
        }
    };
}
