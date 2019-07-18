package com.example.mvptest.mvp.newsfra.model;

import com.example.mvptest.object.NewsObj;
import com.example.mvptest.object.Weather;

import java.util.List;

public  interface NewsmodelDao {
    public void getNews( List<NewsObj> list);//获取数据
    public void getWeather(Weather weather);//获取天气
}
