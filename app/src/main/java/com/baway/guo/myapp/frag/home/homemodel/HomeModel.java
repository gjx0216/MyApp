package com.baway.guo.myapp.frag.home.homemodel;

import com.baway.guo.myapp.frag.home.entity.BannerEntity;
import com.baway.guo.myapp.frag.home.entity.HomeEntity;
import com.baway.guo.myapp.frag.home.homeservice.HomeService;
import com.baway.guo.myapp.net.OkHttpUtil;
import com.baway.guo.myapp.net.httpmvp.HttpListener;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeModel {
    public void getBanner(String url, final HttpListener httpListener) {
        OkHttpUtil.retrofit.create(HomeService.class).getBanner(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BannerEntity bannerEntity) {
                        httpListener.BannerHttpSuccess(bannerEntity);
                    }
                });
    }


    public void getHome(String url, final HttpListener httpListener) {
        OkHttpUtil.retrofit.create(HomeService.class).getHome(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomeEntity homeEntity) {
                        httpListener.HomeHttpSuccess(homeEntity);
                    }
                });
    }
}
