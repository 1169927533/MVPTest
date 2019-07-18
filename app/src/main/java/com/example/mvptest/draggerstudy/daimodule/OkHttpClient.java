package com.example.mvptest.draggerstudy.daimodule;

public class OkHttpClient {
    private int cacheSize;

    public OkHttpClient() {

    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public int getCacheSize() {
        return cacheSize;
    }
}
