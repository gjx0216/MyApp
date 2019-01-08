package com.baway.guo.myapp.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class OkHttpUtil {


    private static OkHttpClient okHttpClient;
    public static Retrofit retrofit;

    private OkHttpUtil() {

    }

    public static void init() {
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(3000, TimeUnit.MILLISECONDS)
                .writeTimeout(3000, TimeUnit.MILLISECONDS)
                .addInterceptor(new OkHeaderInterceptor())
                .addInterceptor(new OkLogInterceptor())
                .build();


        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.PATH)
                .client(okHttpClient)
                .build();
    }
}
