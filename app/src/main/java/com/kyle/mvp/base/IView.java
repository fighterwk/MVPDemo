package com.kyle.mvp.base;

import android.view.View;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/12
 */
public interface IView {

    void attachView(IPresenter presenter, View view);

}
