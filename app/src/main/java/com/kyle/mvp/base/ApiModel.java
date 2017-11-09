package com.kyle.mvp.base;

import com.kyle.mvp.BaseApplication;
import com.kyle.mvp.api.CommonApi;
import com.kyle.mvp.net.CallbackWrapper;

import retrofit2.Call;

/**
 * @Description描述: 负责网络请求数据层
 * @Author作者: Kyle
 * @Date日期: 2017/11/8
 */
public class ApiModel implements IModel {

    protected final <T> Call<T> request(Call<T> call, final CallbackWrapper<T> callback) {
        callback.onStart();
        call.enqueue(callback);
        return call;
    }

    public CommonApi getCommonApi() {
        return BaseApplication.getInstance().createRetrofitService(CommonApi.class);
    }
}
