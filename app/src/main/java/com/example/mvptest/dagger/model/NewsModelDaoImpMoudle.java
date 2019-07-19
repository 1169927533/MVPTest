package com.example.mvptest.dagger.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;


@Module
public class NewsModelDaoImpMoudle {

    @Singleton
    @Provides
    OkHttpClient provideOkHttpCliemt(){
        return new OkHttpClient();
    }
}
