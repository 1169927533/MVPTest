package com.example.mvptest.dagger.model;

import com.example.mvptest.mvp.newsfra.view.NewsView;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsFragmentModel {

    NewsView newsView;

    public NewsFragmentModel(NewsView newsView) {
        this.newsView = newsView;
    }
    @Provides
    NewsView provideNewsView(){
        return newsView;
    }
}
