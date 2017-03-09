package template.simple.oden.com.odensimpletemplate.mvp.template;

import javax.inject.Inject;

import template.simple.oden.com.odensimpletemplate.base.BasePresenter;

/**
 * 项目名称：OdenSimpleTemplate
 * 类描述：
 * 创建人：oden
 * 创建时间：2017/3/9
 */
public class TempLatePresenter extends BasePresenter<TempLateContract.View> {

    @Inject
    public TempLatePresenter(TempLateContract.View rootView) {
        super(rootView);
    }


    public void getTime() {
        mRootView.setTime(System.currentTimeMillis() + "");
    }

}
