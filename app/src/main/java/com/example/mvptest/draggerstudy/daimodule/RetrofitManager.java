package com.example.mvptest.draggerstudy.daimodule;

public class RetrofitManager {
    private OkHttpClient client;

    public OkHttpClient getClient() {
        return client;
    }

    public RetrofitManager(OkHttpClient client) {
        this.client = client;

    }
}
