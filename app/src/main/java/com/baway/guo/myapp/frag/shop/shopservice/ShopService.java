package com.baway.guo.myapp.frag.shop.shopservice;

import com.baway.guo.myapp.frag.shop.entity.QuerygoucarEntity;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 郭佳兴
 **/
public interface ShopService {

    @GET
    Observable<QuerygoucarEntity> getQuery(@Url String url);
}
