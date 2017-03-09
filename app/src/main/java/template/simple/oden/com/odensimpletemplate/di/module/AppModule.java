package template.simple.oden.com.odensimpletemplate.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 项目名称：OdenSimpleTemplate
 * 类描述：
 * 创建人：oden
 * 创建时间：2017/3/9
 */
@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return mApplication;
    }
}
