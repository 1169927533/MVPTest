package com.example.mvptest.draggerstudy.daimodule;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

public class HttpActivity extends AppCompatActivity {
    @Inject
    OkHttpClient okHttpClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerHttpActivityComponent.create().inject(this);
    }
}
