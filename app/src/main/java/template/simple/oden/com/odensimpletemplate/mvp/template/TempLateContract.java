package template.simple.oden.com.odensimpletemplate.mvp.template;

import template.simple.oden.com.odensimpletemplate.base.BaseView;

/**
 * 项目名称：OdenSimpleTemplate
 * 类描述：
 * 创建人：oden
 * 创建时间：2017/3/9
 */
public interface TempLateContract {

    interface View extends BaseView {
        void setTime(String time);
    }


}
