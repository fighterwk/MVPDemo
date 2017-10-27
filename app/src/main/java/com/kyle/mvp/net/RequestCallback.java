package com.kyle.mvp.net;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/27
 */
public abstract class RequestCallback<T> implements Callback<T> {

    public abstract void onStart();

    public abstract void onComplete();

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        onComplete();
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onComplete();
    }
}
