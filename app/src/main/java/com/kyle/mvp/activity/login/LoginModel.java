package com.kyle.mvp.activity.login;

import com.kyle.mvp.base.ApiModel;
import com.kyle.mvp.bean.GitBean;
import com.kyle.mvp.net.CallbackWrapper;
import com.kyle.mvp.net.RequestCallback;

import retrofit2.Call;

/**
 * @Description描述: 这里相当于是做了一个数据的桥接.有点像代理模式中的代理
 * @Author作者: Kyle
 * @Date日期: 2017/10/12
 */
public class LoginModel extends ApiModel {

    public Call<GitBean> login(String account, String password, RequestCallback<GitBean> callback) {
        Call<GitBean> call = request(getCommonApi().login(account, password),
                new CallbackWrapper<GitBean>(callback));
        return call;
    }
}
