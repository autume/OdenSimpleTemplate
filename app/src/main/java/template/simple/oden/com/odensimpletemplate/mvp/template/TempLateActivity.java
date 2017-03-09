package template.simple.oden.com.odensimpletemplate.mvp.template;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import template.simple.oden.com.odensimpletemplate.R;
import template.simple.oden.com.odensimpletemplate.base.BaseActivity;
import template.simple.oden.com.odensimpletemplate.di.component.AppComponent;
import template.simple.oden.com.odensimpletemplate.di.component.DaggerTempLateComponent;
import template.simple.oden.com.odensimpletemplate.di.module.TempLateModule;

/**
 * 项目名称：OdenSimpleTemplate
 * 类描述：
 * 创建人：oden
 * 创建时间：2017/3/9
 */
public class TempLateActivity extends BaseActivity<TempLatePresenter> implements TempLateContract.View{

    Button btnGetTime;
    TextView tvTime;

    @Override
    protected void ComponentInject(AppComponent appComponent) {
        DaggerTempLateComponent
                .builder()
                .appComponent(appComponent)
                .tempLateModule(new TempLateModule(this)) //请将TempLateModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_template, null, false);
    }

    @Override
    protected void initData() {
        btnGetTime = (Button) findViewById(R.id.btn_get_time);
        tvTime = (TextView) findViewById(R.id.tv_time);

        btnGetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getTime();
            }
        });
    }

    @Override
    public void setTime(String time) {
        tvTime.setText(time);
    }

}
