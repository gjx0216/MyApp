package com.baway.guo.myapp.login.loginretrofit;

import com.baway.guo.myapp.login.loginentity.LoginEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface LoginRetrofitApi<T> {
    //登录
    @POST
    @FormUrlEncoded
    Call<LoginEntity> postMethod(@Field("phone") String phone, @Field("pwd") String pwd, @Url String url);
}
