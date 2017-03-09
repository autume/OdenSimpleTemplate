package template.simple.oden.com.odensimpletemplate.di.module;

import dagger.Module;
import dagger.Provides;
import template.simple.oden.com.odensimpletemplate.di.scop.ActivityScope;
import template.simple.oden.com.odensimpletemplate.mvp.template.TempLateContract;

/**
 * 项目名称：OdenSimpleTemplate
 * 类描述：
 * 创建人：oden
 * 创建时间：2017/3/9
 */
@Module
public class TempLateModule {
    private TempLateContract.View view;

    public TempLateModule(TempLateContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    TempLateContract.View provideTempLateView() {
        return this.view;
    }
}
