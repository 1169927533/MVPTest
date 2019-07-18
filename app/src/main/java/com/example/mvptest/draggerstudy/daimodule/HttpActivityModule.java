package com.example.mvptest.draggerstudy.daimodule;

import dagger.Module;
import dagger.Provides;

@Module
public class HttpActivityModule {
    @Provides
    OkHttpClient provideokHttpClient(){
        return new OkHttpClient();
    }

}
