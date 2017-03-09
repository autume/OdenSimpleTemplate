package template.simple.oden.com.odensimpletemplate.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import template.simple.oden.com.odensimpletemplate.app.MyApplication;
import template.simple.oden.com.odensimpletemplate.di.component.AppComponent;

/**
 * 项目名称：OdenSimpleTemplate
 * 类描述：
 * 创建人：oden
 * 创建时间：2017/3/9
 */
public abstract class BaseActivity<P extends BasePresenter> extends Activity {
    protected MyApplication myApplication;

    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initView());

        myApplication = (MyApplication) getApplication();
        ComponentInject(myApplication.getAppComponent());//依赖注入
        initData();
    }

    /**
     * 依赖注入的入口
     */
    protected abstract void ComponentInject(AppComponent appComponent);

    protected abstract View initView();

    protected abstract void initData();

}
