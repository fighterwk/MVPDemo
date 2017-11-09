package com.kyle.mvp;

import android.app.Application;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/12
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;


    private Retrofit retrofit;

    public BaseApplication() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.retrofit = createRetrofit();
    }

    private OkHttpClient createHttpClient() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("http", message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY));
        okBuilder.connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS);
        okBuilder.readTimeout(30, java.util.concurrent.TimeUnit.SECONDS);
        okBuilder.writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS);

        return okBuilder.build();
    }

    private Retrofit createRetrofit() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl("https://api.github.com");
//        retrofitBuilder.baseUrl("http://apiotc-test.jkbat.cn");
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        retrofitBuilder.client(createHttpClient());

        return retrofitBuilder.build();
    }

    public Retrofit getRetrofit() {
        return this.retrofit;
    }

    public <T> T createRetrofitService(Class<T> service) {
        return retrofit.create(service);
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}
