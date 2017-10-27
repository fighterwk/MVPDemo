package com.kyle.mvp.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import com.kyle.mvp.BaseApplication;
import com.kyle.mvp.util.DialogUtil;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/26
 */
public class BaseView implements IView {

    private Dialog loadingDialog;
    protected Context mContext;
    protected IPresenter mPresenter;


    @Override
    public void attachView(IPresenter presenter, View view) {
        this.mPresenter = presenter;
        this.mContext = view.getContext();
    }

    @Override
    public void showToast(CharSequence text) {
        Toast.makeText(BaseApplication.getInstance(), text, Toast.LENGTH_LONG);
    }

    @Override
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
