package com.example.mvptest.draggerstudy.budaimodule;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import javax.inject.Inject;

public class FactoryActivity extends AppCompatActivity {
    /**
     * 在需要注入的类中使用@Inject标注要注入的变量
     */
    @Inject
    Project project;

    @Inject
    Factory factory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * DaggerFactoryActivityComponent只会在build完后才会生产
         * 生成的格式：Dagger+我们所定义的Component的名字
         */
        DaggerFactoryActivityComponent.create().inject(this);
    }
}
