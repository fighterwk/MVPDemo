package com.kyle.mvp.activity.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.kyle.mvp.R;
import com.kyle.mvp.annotion.Layout;
import com.kyle.mvp.base.PresenterActivity;
import com.kyle.mvp.bean.GitBean;
import com.kyle.mvp.net.RequestCallback;

import butterknife.OnClick;
import retrofit2.Call;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/12
 */
@Layout(layoutResId = R.layout.activity_login)
public class LoginActivity extends PresenterActivity<LoginView, LoginModel> {

    Call requestCall;

    @OnClick(R.id.btn_login)
    public void onLoginClicked(View v) {
        String account = this.v.getAccount();
        String pwd = this.v.getPwd();

        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入账号和密码", Toast.LENGTH_SHORT).show();
            getView().setAccount("11");
            getView().setPwd("111");
            return;
        }


        // model
        Call<GitBean> requestCall = getModel().login(getView().getAccount(),
                getView().getPwd(), new RequestCallback<GitBean>() {
                    @Override
                    public void onStart() {
                        getView().showLoadingDialog();
                    }

                    @Override
                    public void onComplete() {
                        getView().dismissLoadingDialog();
                    }

                    @Override
                    public void onSuccess(GitBean bean) {
                        getView().showToast("登录成功:" + bean);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        getView().showToast("登录失败:" + e.getMessage());
                    }
                });
        this.requestCall = requestCall;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (requestCall != null && !requestCall.isCanceled()) {
            requestCall.cancel();
            requestCall = null;
        }
    }
}