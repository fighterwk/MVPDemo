package com.kyle.mvp.base;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/12
 */
public interface IPresenter<V extends IView, M> {

    void setView(V v);

    V getView();

    void setModel(M m);

    M getModel();

}
