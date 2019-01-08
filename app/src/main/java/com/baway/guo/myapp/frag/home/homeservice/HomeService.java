package com.baway.guo.myapp.frag.home.homeservice;

import com.baway.guo.myapp.frag.home.entity.AddCarEntity;
import com.baway.guo.myapp.frag.home.entity.BannerEntity;
import com.baway.guo.myapp.frag.home.entity.DetilEntity;
import com.baway.guo.myapp.frag.home.entity.HomeEntity;
import com.baway.guo.myapp.frag.home.entity.HomeSouEntity;
import com.baway.guo.myapp.frag.home.entity.MoliguiEntity;
import com.baway.guo.myapp.frag.home.entity.PinzhiGuiEntity;
import com.baway.guo.myapp.frag.home.entity.PopErEntity;
import com.baway.guo.myapp.frag.home.entity.PopYiEntity;
import com.baway.guo.myapp.frag.home.entity.RexiaoguiEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface HomeService {
    @GET
    Observable<BannerEntity> getBanner(@Url String url);

    //Homeshuju
    @GET
    Observable<HomeEntity> getHome(@Url String url);

    //热销归属
    @GET
    Observable<RexiaoguiEntity> getRexiao(@Url String url);

    //魔力归属
    @GET
    Observable<MoliguiEntity> getMoli(@Url String url);

    //品质归属
    @GET
    Observable<PinzhiGuiEntity> getPinzhi(@Url String url);

    @GET
    Observable<PopYiEntity> getMethodpop(@Url String url);

    @GET
    Observable<PopErEntity> getMethodpopEr(@Url String url, @Query("firstCategoryId") String firstCategoryId);

    //搜索
    @GET
    Observable<HomeSouEntity> getSou(@Url String url);

    //商品详情
    @GET
    Observable<DetilEntity> getDetil(@Url String url);

    //同步购物车
    @PUT
    @FormUrlEncoded
    Observable<AddCarEntity> AddCar(@Url String url, @Field("data") String data);

}
