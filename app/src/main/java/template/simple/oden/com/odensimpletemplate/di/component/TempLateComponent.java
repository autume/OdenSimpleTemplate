package template.simple.oden.com.odensimpletemplate.di.component;

import dagger.Component;
import template.simple.oden.com.odensimpletemplate.di.module.TempLateModule;
import template.simple.oden.com.odensimpletemplate.di.scop.ActivityScope;
import template.simple.oden.com.odensimpletemplate.mvp.template.TempLateActivity;

/**
 * 项目名称：OdenSimpleTemplate
 * 类描述：
 * 创建人：oden
 * 创建时间：2017/3/9
 */
@ActivityScope
@Component(modules = TempLateModule.class, dependencies = AppComponent.class)
public interface TempLateComponent {
    void inject(TempLateActivity activity);
}
