package com.kyle.mvp.activity.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kyle.mvp.R;
import com.kyle.mvp.annotion.Layout;
import com.kyle.mvp.base.IView;
import com.kyle.mvp.base.PresenterActivity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/12
 */
@Layout(layoutResId = R.layout.activity_login)
public class LoginActivity extends PresenterActivity<LoginView, LoginModel> {

    ReferenceQueue<Call> callReferenceQueue;


    @OnClick(R.id.btn_login)
    public void onLoginClicked(View v) {
        String account = this.v.getAccount();
        String pwd = this.v.getPwd();

        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入账号和密码", Toast.LENGTH_SHORT).show();
            return;
        }


        // model
        enqueueCall(this.m.login(account, pwd));
    }


    protected void enqueueCall(Call call) {
        RequestCallback requestCallback = new RequestCallback(this.v);
        requestCallback.onStart();
        call.enqueue(requestCallback);
    }

    // P
    private static class RequestCallback extends com.kyle.mvp.net.RequestCallback<Gson> {
        WeakReference<IView> view;

        public RequestCallback(IView view) {
            this.view = new WeakReference<>(view);
        }

        @Override
        public void onStart() {
            if (view.get() != null) {
                view.get().showLoadingDialog();
            }
        }

        @Override
        public void onComplete() {
            if (view.get() != null) {
                view.get().dismissLoadingDialog();
            }
        }

        @Override
        public void onResponse(Call<Gson> call, Response<Gson> response) {
            super.onResponse(call, response);
            if (view.get() != null) {
                view.get().showToast("登录成功");
            }
        }

        @Override
        public void onFailure(Call<Gson> call, Throwable t) {
            super.onFailure(call, t);
            if (view.get() != null) {
                view.get().showToast("登录失败");
            }
        }
    }


}