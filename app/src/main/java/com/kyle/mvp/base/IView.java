package com.kyle.mvp.base;

/**
 * @Description描述: View层用于显示数据，获取用户的交互事件然后传递到presenter中
 * @Author作者: Kyle
 * @Date日期: 2017/10/12
 */
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
