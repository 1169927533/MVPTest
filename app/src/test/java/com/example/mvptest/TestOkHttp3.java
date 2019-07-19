package com.example.mvptest;

import com.example.mvptest.net.Net;
import com.example.mvptest.util.okhttp分装.BaseRequest;
import com.example.mvptest.util.okhttp分装.IRequest;
import com.example.mvptest.util.okhttp分装.OkHttpClientImpl;
import com.example.mvptest.util.okhttp分装.my.Comback;
import com.example.mvptest.util.okhttp分装.my.HttpUtil;

import org.junit.Test;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestOkHttp3 {

    @Test
    public void testOkHttp3() {
        final OkHttpClientImpl httpClient = new OkHttpClientImpl();
        String url = Net.news_Netease + "/getImages?page=1&count = 5";
        final IRequest request = new BaseRequest(url);
        httpClient.get(request, false);
    }

    void dsa() {
        String url = Net.news_Netease + "/getImages?page=1&count = 5";

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

        //最想实现的效果
        /**
         * okhtpclient.get(url).callback(new Callback（）{
         *
         *     success
         *
         *     error
         *
         * });
         */
    }

    @Test
    public  void testthree() {
        String url = Net.news_Netease + "/getImages?page=1&count = 5";
        HttpUtil.getInstance().get(url, new Comback() {
            @Override
            public void success_back(String content) {
                System.out.println(content);
            }

            @Override
            public void error_back() {

            }
        });
    }

}
