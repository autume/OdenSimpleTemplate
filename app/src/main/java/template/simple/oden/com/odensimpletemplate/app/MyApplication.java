package template.simple.oden.com.odensimpletemplate.app;

import android.app.Application;

import template.simple.oden.com.odensimpletemplate.di.component.AppComponent;
import template.simple.oden.com.odensimpletemplate.di.component.DaggerAppComponent;
import template.simple.oden.com.odensimpletemplate.di.module.AppModule;

/**
 * 项目名称：OdenSimpleTemplate
 * 类描述：
 * 创建人：oden
 * 创建时间：2017/3/9
 */
public class MyApplication extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
