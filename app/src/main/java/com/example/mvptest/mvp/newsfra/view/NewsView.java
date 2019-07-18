package com.example.mvptest.mvp.newsfra.view;

import com.example.mvptest.object.NewsObj;
import com.example.mvptest.object.Weather;

import java.util.List;

public interface NewsView {
    public void show_news(List<NewsObj> list);//展示数据
    public void show_weather(Weather weather);//展示天气
}
