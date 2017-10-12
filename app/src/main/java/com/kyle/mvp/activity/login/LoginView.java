package com.kyle.mvp.activity.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kyle.mvp.R;
import com.kyle.mvp.base.IPresenter;
import com.kyle.mvp.base.IView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description描述:View 的职责主要是渲染UI，控制动画
 * @Author作者: Kyle
 * @Date日期: 2017/10/12
 */
public class LoginView implements IView {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    public void attachView(IPresenter presenter, View view) {
        ButterKnife.bind(this, view);
    }

    public String getAccount() {
        return etAccount.getText().toString().trim();
    }

    public String getPwd() {
        return etPwd.getText().toString().trim();
    }

}
