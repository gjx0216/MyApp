package com.baway.guo.myapp.frag.circle.service;

import com.baway.guo.myapp.frag.circle.bean.AddEntity;
import com.baway.guo.myapp.frag.circle.bean.CricleEntity;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;
import rx.Observer;

public interface CricleService {
    //查询圈子列表
    @GET("small/circle/v1/findCircleList")
    Observable<CricleEntity> getCricle(@Query("page") int page, @Query("count") int count);

    //点赞
    @POST
    @FormUrlEncoded
    Call<String> getZan(@Url String url, @Field("circleId") int circleId);

    //取消点赞
    @DELETE
    Call<String> getQuZan(@Url String url, @Query("circleId") int circleId);
}
