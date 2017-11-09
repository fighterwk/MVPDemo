package com.kyle.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kyle.mvp.annotion.Layout;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import butterknife.ButterKnife;

/**
 * @Description描述: MVP可以应用到对应的功能模块中，在這里我将 Activity 作为一个简单的功能模块.
 * Activity 作为一个功能模块的入口, 在MVC模式中它担任了View 和Control的两个角色。
 * 这里我将它的角色安排为 Client 和 Presenter，将原来View 的角色分担给真正的View 去担任.
 * <p>
 * Client 角色的责任: 组合 Model View Presenter 的关系，并且控制着整个结构的生命周期.
 * @Author作者: Kyle
 * @Date日期: 2017/10/12
 */
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
