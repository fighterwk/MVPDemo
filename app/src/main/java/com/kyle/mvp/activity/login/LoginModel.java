package com.kyle.mvp.activity.login;

import com.google.gson.Gson;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/12
 */
public interface LoginModel {

    @POST("user")
    @FormUrlEncoded
    Call<Gson> login(@Field(value = "account") String account, @Field("password") String password);


    @POST
    @FormUrlEncoded
    Call<Gson> post(@Url String url, @FieldMap Map<String, String> params);

    @GET
    Call<Gson> get(@Url String url, @QueryMap Map<String, String> params);
}
