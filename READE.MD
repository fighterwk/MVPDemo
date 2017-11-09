# 我理解的MVP
MVP是什么呢，MVP是MVC的变异体，降低了MVC的耦合度。达到: 低耦合、高复用、易测试、好维护。

为了更好的理解MVP，我通过责任将它们进行拆分

### Model的职责
负责网络接口数据请求、本地持久化数据的读取和存储、数据库访问等

### View的职责
数据显示、数据采集、UI交互动画、将用户事件路由给Presenter等

### Presenter的职责
业务逻辑控制、View和Model的传话筒


### 在Android中如何使用MVP模式呢?
定义View层
```java
    public interface IView {

        /**
         * 依附Presenter
         *
         * @param presenter
         */
        void setPresenter(IPresenter presenter);

        /**
         * Toast提示
         *
         * @param text
         */
        void showToast(CharSequence text);


        /**
         * 显示加载框
         *
         * @param text 提示文本
         */
        void showLoadingDialog(CharSequence text);


        /**
         * 显示无提示文本的加载框
         */
        void showLoadingDialog();

        /**
         * 加载框完成
         */
        void dismissLoadingDialog();


    }
```

定义Presenter层
```java
    public interface IPresenter<V extends IView, M> {

        void setView(V v);

        V getView();

        void setModel(M m);

        M getModel();

    }
```

定义Model层
```java
public interface IModel {

}
```
根据MVP的概念设计出了接口



如何在Android中如何去实现接口呢?
* Presenter层
使用Activity作为Presenter层，在官方的MVPdemo中，activity是做为View层的，为什么我要看做P层呢，因为我认为activity
虽然是一个窗体，但是它并没有做到View显示的作用，大多数的工作都是在逻辑控制上，如:页面跳转、接收上一个页面传递过来的数据、
启动广播、控制整个activity关联类的声明周期。

```java
public abstract class PresenterActivity<V extends BaseView, M extends IModel> extends AppCompatActivity
        implements IPresenter<V, M> {

    protected V v;
    protected M m;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        beforeCreate(savedInstanceState);
        super.onCreate(savedInstanceState);

        Class<? extends PresenterActivity> aClass = this.getClass();
        Layout annotation = aClass.getAnnotation(Layout.class);
        if (annotation != null) {
            int layoutResId = annotation.layoutResId();
            setContentView(layoutResId);
        }

        ButterKnife.bind(this);
        this.createRelationshipClass();

        afterCreate(savedInstanceState);
    }

    /**
     * onCreate 方法调用之前
     *
     * @param savedInstanceState
     */
    protected void beforeCreate(Bundle savedInstanceState) {
    }

    /**
     * onCreate 方法调用之后
     *
     * @param savedInstanceState
     */
    protected void afterCreate(Bundle savedInstanceState) {
    }


    @Override
    public V getView() {
        return v;
    }

    @Override
    public M getModel() {
        return m;
    }


    @Override
    public void setView(V v) {
        this.v = v;
    }

    @Override
    public void setModel(M m) {
        this.m = m;
    }

    /**
     * 组合MVP的关系
     */
    private void createRelationshipClass() {
        final IPresenter presenter = this;
        IView view = null;
        Object model = null;

        // 通过声明的泛型创建Model 和View实例化
        Class<? extends PresenterActivity> clz = this.getClass();
        try {
            Type genericSuperclass = clz.getGenericSuperclass();
            if (genericSuperclass instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (actualTypeArguments.length == 2) {
                    Type viewType = actualTypeArguments[0];// view
                    if (viewType instanceof Class
                            && BaseView.class.isAssignableFrom((Class<?>) viewType)) {
                        Class viewClz = (Class) viewType;
                        Constructor constructor = viewClz.getConstructor(View.class);
                        view = (BaseView) constructor.newInstance(findViewById(android.R.id.content));
                    } else {
                        throw new RuntimeException("View 类型创建错误，必须是BaseView的子类");
                    }
                    Type modelType = actualTypeArguments[1];// model

                    if (modelType instanceof Class) {
                        if (VoidModel.class.isAssignableFrom((Class<?>) modelType)) {
                            model = null;  // voidModel 类型，忽略创建
                        } else {
                            model = ((Class) modelType).newInstance();
                        }
                    } else {
                        throw new RuntimeException("Model 类型创建错误，必须是IModel的子类");
                    }
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 组合MVP 关系.
        if (view != null) {
            view.setPresenter(presenter);
            presenter.setView(view);
        }

        if (model != null) {
            presenter.setModel(model);
        }

    }
}
```

* View层
原则: 一个layout文件对应一个View对象,在layout中使用include引入的layout，对应在View中使用组合模式创建.
如: 在每个页面都存在一个相同的title，为了方便维护，一般都是先定义一个 layout_title.xml布局文件，在activity_xxx.xml
布局中通过include标签进行引入 layout_title.xml，这样的方式，对应 View层，我希望的是分别对应一个 TitleView 和xxxView.
在layout布局中是activity_xxx.xml包含layout_title.xml关系。在代码中就是

```java
XxxView{
    TitleView titleView;
}
```

定义一个针对android View的基础类
```java
public class BaseView implements IView {

    private Dialog loadingDialog;
    protected Context mContext;
    protected IPresenter mPresenter;
    protected View rootView;

    public BaseView(View view) {
        this.rootView = view;
        this.mContext = this.rootView.getContext();
        ButterKnife.bind(this, view);
    }

    @Override
    public void setPresenter(IPresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showToast(CharSequence text) {
        Toast.makeText(BaseApplication.getInstance(), text, Toast.LENGTH_LONG).show();
    }


    /**
     * 醒目提示框
     *
     * @param text          提示文本
     * @param actionName    按钮名称
     * @param clickListener
     */
    public void showNoticeDialog(CharSequence text, CharSequence actionName,
                                 DialogInterface.OnClickListener clickListener) {
        DialogUtil.showNoticeDialog(mContext, text, actionName, clickListener);
    }

    @Override
    public void showLoadingDialog(CharSequence text) {
        dismissLoadingDialog();
        loadingDialog = DialogUtil.createLoadingDialog(mContext, text);
        loadingDialog.show();
    }

    @Override
    public void showLoadingDialog() {
        showLoadingDialog("Loading...");
    }

    @Override
    public void dismissLoadingDialog() {
        DialogUtil.dismissDialog(loadingDialog);
    }
}
```

* Model层
这个就相对简单了。

定义一个针对Retrofit库的apimodel

```java
public class ApiModel implements IModel {

    protected final <T> Call<T> request(Call<T> call, final CallbackWrapper<T> callback) {
        callback.onStart();
        call.enqueue(callback);
        return call;
    }

    public CommonApi getCommonApi() {
        return BaseApplication.getInstance().createRetrofitService(CommonApi.class);
    }
}

```



