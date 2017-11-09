package com.kyle.mvp.api;

import com.google.gson.Gson;
import com.kyle.mvp.bean.GitBean;

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
 * @Description描述: api接口的集合
 * @Author作者: Kyle
 * @Date日期: 2017/11/8
 */
public interface CommonApi {

    @POST("user")
    @FormUrlEncoded
    Call<GitBean> login(@Field(value = "account") String account, @Field("password") String password);


    @POST
    @FormUrlEncoded
    Call<Gson> post(@Url String url, @FieldMap Map<String, String> params);

    @GET
    Call<Gson> get(@Url String url, @QueryMap Map<String, String> params);

}
