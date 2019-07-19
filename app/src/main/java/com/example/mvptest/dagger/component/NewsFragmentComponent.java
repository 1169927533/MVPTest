package com.example.mvptest.dagger.component;

import com.example.mvptest.dagger.model.NewsFragmentModel;
import com.example.mvptest.page.fragment.NewsFragment;

import dagger.Component;

@Component(modules = NewsFragmentModel.class)
public interface NewsFragmentComponent {
    public void inject(NewsFragment fragment);
}
