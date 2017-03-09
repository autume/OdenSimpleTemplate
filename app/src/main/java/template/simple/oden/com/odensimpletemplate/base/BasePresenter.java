package template.simple.oden.com.odensimpletemplate.base;

/**
 * 项目名称：OdenSimpleTemplate
 * 类描述：
 * 创建人：oden
 * 创建时间：2017/3/9
 */
public class BasePresenter<V extends BaseView>  implements presenter{
    protected V mRootView;

    public BasePresenter(V rootView) {
        this.mRootView = rootView;
        onStart();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

    }
}
