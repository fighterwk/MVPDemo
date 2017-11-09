package com.kyle.mvp.base;

/**
 * @Description描述:View 和Model的中间层，功能:断开Model 和View之间的通信，担任一个Model 和View的传话员.
 * 从而减少了Model 和View 的耦合。
 * @Author作者: Kyle
 * @Date日期: 2017/10/12
 */
public interface IPresenter<V extends IView, M> {

    void setView(V v);

    V getView();

    void setModel(M m);

    M getModel();

}
