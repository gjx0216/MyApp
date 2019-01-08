package com.baway.guo.myapp.reg.regretrofitapi;

import com.baway.guo.myapp.reg.regentity.RegEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface Regretrofitapi {

    //注册
    @POST
    @FormUrlEncoded
    Call<RegEntity> postMethod0(@Field("phone") String phone, @Field("pwd") String pwd, @Url String url);

}
