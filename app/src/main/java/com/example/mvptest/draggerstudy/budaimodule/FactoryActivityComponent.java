package com.example.mvptest.draggerstudy.budaimodule;

import dagger.Component;
/**
 * Component是注入者与被注入者之间联系的桥梁
 * 有了它dragger才知道把谁注入到什么地方，不可缺少的
 */
/**
 * 编写Component接口使用@Component进行标注
 * 里面的void inject()的参数表示要将依赖注入到的目标位置
 */
@Component
public interface FactoryActivityComponent {
    void inject(FactoryActivity factoryActivity);
}
