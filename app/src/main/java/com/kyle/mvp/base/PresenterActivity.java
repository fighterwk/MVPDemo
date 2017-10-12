package com.kyle.mvp.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kyle.mvp.BaseApplication;
import com.kyle.mvp.annotion.Layout;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import butterknife.ButterKnife;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/12
 */
public abstract class PresenterActivity<V extends IView, M> extends AppCompatActivity
        implements IPresenter<V, M> {

    protected V v;
    protected M m;

    private Dialog progressDialog;

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

    protected void showProgress() {
        dismissProgress();
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
    }

    protected void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
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

    protected <T> T createRetrofitService(Class<T> service) {
        return BaseApplication.getInstance().createRetrofitService(service);
    }

    /**
     * 创建关系类
     */
    private void createRelationshipClass() {
        final IPresenter presenter = this;
        IView view = null;
        Object model = null;

        Class<? extends PresenterActivity> clz = this.getClass();
        try {
            Type genericSuperclass = clz.getGenericSuperclass();
            if (genericSuperclass instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (actualTypeArguments.length == 2) {
                    Type viewType = actualTypeArguments[0];// view
                    if (viewType instanceof Class) {
                        view = (IView) ((Class) viewType).newInstance();
                    }
                    Type modelType = actualTypeArguments[1];// model
                    if (IModel.class.isAssignableFrom((Class) modelType)) {
                        model = null;
                    } else {
                        model = createRetrofitService((Class) modelType);
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

        if (view != null) {
            view.attachView(presenter, findViewById(android.R.id.content));
            presenter.setView(view);
        }

        if (model != null) {
            presenter.setModel(model);
        }

    }
}
