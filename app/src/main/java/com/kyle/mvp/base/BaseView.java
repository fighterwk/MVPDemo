package com.kyle.mvp.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import com.kyle.mvp.BaseApplication;
import com.kyle.mvp.util.DialogUtil;

import butterknife.ButterKnife;

/**
 * @Description描述: android View 层
 * @Author作者: Kyle
 * @Date日期: 2017/10/26
 */
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
