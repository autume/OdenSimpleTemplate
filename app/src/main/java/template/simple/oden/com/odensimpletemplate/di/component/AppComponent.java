package template.simple.oden.com.odensimpletemplate.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import template.simple.oden.com.odensimpletemplate.di.module.AppModule;

/**
 * 项目名称：OdenSimpleTemplate
 * 类描述：
 * 创建人：oden
 * 创建时间：2017/3/9
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Application Application();
}
