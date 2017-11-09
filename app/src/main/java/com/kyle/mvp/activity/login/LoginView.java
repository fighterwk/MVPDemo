package com.kyle.mvp.activity.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kyle.mvp.R;
import com.kyle.mvp.base.BaseView;

import butterknife.BindView;

/**
 * @Description描述:View 的职责主要是渲染UI，控制动画
 * @Author作者: Kyle
 * @Date日期: 2017/10/12
 */
public class LoginView extends BaseView {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;

    public LoginView(View view) {
        super(view);
    }

    public void setAccount(String account){
        etAccount.setText(account);
    }

    public String getAccount() {
        return etAccount.getText().toString().trim();
    }

    public String getPwd() {
        return etPwd.getText().toString().trim();
    }

    public void setPwd(String pwd){
        etPwd.setText(pwd);
    }

}
