package com.example.mvptest.dagger.component;

import com.example.mvptest.dagger.model.NewsModelDaoImpMoudle;
import com.example.mvptest.mvp.newsfra.model.NewsmodelDaoImp;
import com.example.mvptest.page.fragment.NewsFragment;
import com.example.mvptest.util.okhttp分装.my.HttpUtil;

import dagger.Component;


@Component(modules = NewsModelDaoImpMoudle.class)
public interface HttpUtilComponent {
    public void inject(NewsmodelDaoImp httpUtil);
}
