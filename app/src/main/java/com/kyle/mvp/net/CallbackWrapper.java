package com.kyle.mvp.net;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/11/8
 */
public class CallbackWrapper<T> implements Callback<T> {
    RequestCallback callback;

    public CallbackWrapper(RequestCallback callback) {
        this.callback = callback;
    }

    public void onStart() {
        callback.onStart();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        callback.onComplete();
        if (response.isSuccessful()) {
            callback.onSuccess(response.body());
        }else{
            callback.onFail(new Exception("请求失败:" + response.code()));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        callback.onComplete();
        callback.onFail(t);
    }
}
