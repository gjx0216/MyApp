package com.baway.guo.myapp.frag.home.homemodel;

import com.baway.guo.myapp.frag.home.entity.HomeSouEntity;
import com.baway.guo.myapp.frag.home.homeservice.HomeService;
import com.baway.guo.myapp.net.OkHttpUtil;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SouModel {
    public void SouModel(String url, final HttpSou httpSou) {
        OkHttpUtil.retrofit.create(HomeService.class).getSou(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeSouEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomeSouEntity homeSouEntity) {
                        httpSou.getSou(homeSouEntity);
                    }
                });
    }

    public interface HttpSou {
        void getSou(HomeSouEntity name);
    }
}
