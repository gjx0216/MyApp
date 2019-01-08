package com.baway.guo.myapp.frag.mine.mineservice;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 郭佳兴
 **/
public interface MineService {
    //设置上传头像
    @POST
    @Multipart
    Call<String> SetIcon(@Url String url, @Part MultipartBody.Part body);

    //修改昵称
    @PUT
    @FormUrlEncoded
    Observable<String> UpdNickname(@Url String url, @Field("nickName") String nickName);

    //修改密码
    @PUT
    @FormUrlEncoded
    Observable<String> Updpwd(@Url String url, @Field("oldPwd") String oldPwd, @Field("newPwd") String newPwd);

    //添加收货地址
    @POST
    @FormUrlEncoded
    Call<String> Addaddresses(@Field("realName") String realName, @Field("phone") String phone, @Field("address") String address, @Field("zipCode") String zipCode, @Url String url);

    //修改收货地址
    @PUT
    @FormUrlEncoded
    Call<String> Updaddresses(@Url String url, @Field("id") int id, @Field("realName") String realName, @Field("phone") String phone, @Field("address") String address, @Field("zipCode") String zipCode);

    //设置默认地址
    @POST
    @FormUrlEncoded
    Call<String> setAddress(@Url String url, @Field("id") int id);

    @GET
    Observable<String> getCricle(@Url String url);

    @GET
    Observable<String> getCricledel(@Url String url);

}
