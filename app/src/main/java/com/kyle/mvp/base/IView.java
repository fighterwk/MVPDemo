package com.kyle.mvp.base;

import android.content.DialogInterface;
import android.view.View;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/12
 */
public interface IView {

    /**
     * @param presenter
     * @param view
     */
    void attachView(IPresenter presenter, View view);

    /**
     * Toast提示
     *
     * @param text
     */
    void showToast(CharSequence text);

    /**
     * 醒目提示框
     *
     * @param text          提示文本
     * @param actionName    按钮名称
     * @param clickListener
     */
    void showNoticeDialog(CharSequence text, CharSequence actionName, DialogInterface.OnClickListener clickListener);

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
