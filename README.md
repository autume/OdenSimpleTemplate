# 利用Dagger构建的简易MVP框架
## 简介
本文使用dagger2构建MVP框架，目的是加深dagger2的理解，一个小demo，记录分享之。
相关文章：
[Android Mvp实践](http://blog.csdn.net/yaodong379/article/details/51184460)
[Android中利用泛型简化MVP](http://blog.csdn.net/yaodong379/article/details/52904535)

## 总体框架
### 工程目录结构
整个工程的目录结构如下：
![](http://i.imgur.com/fNGSPTr.png)
base文件夹存放一些公用的基类文件，di文件夹存放依赖注入相关的代码，mvp中按功能模块划分。本demo实现的功能为：通过点击界面上的按钮，获取手机系统时间并显示。

## 具体实现
### 在项目中引入dagger
```java
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
    }
}

apply plugin: 'com.android.application' 
apply plugin: 'com.neenbedankt.android-apt'

dependencies {
    //other dependencies

    //Dagger2
    compile 'com.google.dagger:dagger:2.0.2'
    compile 'javax.annotation:jsr250-api:1.0'
    apt 'com.google.dagger:dagger-compiler:2.0.2'
}
```
### BaseActivity
BaseActivity中传入Presenter，利用@Inject注解Presenter，Dagger2会自动去找Presenter的生成方法，并创建presenter对象，则activity中持有presenter对象，可调用presenter中的方法。
```java
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
```
### BasePresenter
BasePresenter中传入BaseView接口，持有view接口，可调用view中的方法
```java
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
```
### presenter层的具体实现
TempLatePresenter的构造方法使用了@Inject注解，在上面的BaseActivity中自动构造TempLatePresenter对象时就是从这里产生的TempLatePresenter对象，同时该构造方法中还传入了TempLateContract.View参数，因此dagger2会继续寻找TempLateContract.View的生成方法
```java
public class TempLatePresenter extends BasePresenter<TempLateContract.View> {

    @Inject
    public TempLatePresenter(TempLateContract.View rootView) {
        super(rootView);
    }


    public void getTime() {
        mRootView.setTime(System.currentTimeMillis() + "");
    }

}

```
### 依赖注入
@Module中提供TempLateContract.View的构造方法
```java
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
```
TempLateComponent管理该module
```java
@ActivityScope
@Component(modules = TempLateModule.class, dependencies = AppComponent.class)
public interface TempLateComponent {
    void inject(TempLateActivity activity);
}
```
### view层的具体实现
TempLateActivity中调用ComponentInject，实现依赖注入
```java
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
```

整体流程如下：
- TempLateActivity继承BaseActivity并传入TempLatePresenter，view中持有TempLatePresenter对象
- dagger2根据@inject注解知道要构造TempLatePresenter对象
- 找到TempLatePresenter的构造方法，该方法中持有view的引用，发现还需要TempLateContract.View参数
- 在TempLateModule中找到TempLateContract.View的构造方法
- 依次完成TempLateContract.View和TempLatePresenter的构造

[详细内容见CSDN原文](http://blog.csdn.net/yaodong379/article/details/61414333)




