package com.kyle.mvp.activity.login;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/12
 */
public interface LoginModel {

    @POST("user")
    @FormUrlEncoded
    Call<Gson> login(@Field(value = "account") String account, @Field("password") String password);
}
