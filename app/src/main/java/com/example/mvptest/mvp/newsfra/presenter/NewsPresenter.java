package com.example.mvptest.mvp.newsfra.presenter;

import com.example.mvptest.mvp.newsfra.model.NewsmodelDao;
import com.example.mvptest.mvp.newsfra.model.NewsmodelDaoImp;
import com.example.mvptest.mvp.newsfra.view.NewsView;
import com.example.mvptest.object.NewsObj;
import com.example.mvptest.object.Weather;

import java.util.List;

import javax.inject.Inject;

public class NewsPresenter implements NewsmodelDao{

    NewsView newsView;

    NewsmodelDaoImp newsmodelDaoImp;

    int startnum = 1;

    @Inject
    public NewsPresenter(NewsView newsView) {
        this.newsView = newsView;
        newsmodelDaoImp = new NewsmodelDaoImp(this);
    }

    //获取新闻数据
    public void getNews(int endnum){
        newsmodelDaoImp.getNews(startnum,endnum);
        startnum++;
    }

    //获取天气
    public void getWeather(String location){
        newsmodelDaoImp.getWeather(location);
    }

    @Override
    public void getNews(List<NewsObj> list) {
        newsView.show_news(list);
    }

    @Override
    public void getWeather(Weather weather) {
        newsView.show_weather(weather);
    }
}
