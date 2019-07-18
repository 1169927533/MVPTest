package com.example.mvptest.draggerstudy.daimodule;

import dagger.Component;

/**
 * 使用@Component标注这个接口
 * 并使用modules = 的方法链接上第一步编写的Module类
 */
@Component(modules = HttpActivityModule.class)
public interface HttpActivityComponent {
    void inject(HttpActivity httpActivity);
}
