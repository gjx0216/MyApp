package com.baway.guo.myapp.frag.home.homemodel;

import com.baway.guo.myapp.frag.home.entity.PopErEntity;
import com.baway.guo.myapp.frag.home.entity.PopYiEntity;
import com.baway.guo.myapp.frag.home.homeservice.HomeService;
import com.baway.guo.myapp.net.OkHttpUtil;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PopModel {

    public void getPopyi(String url, final HttpPop httpPop) {
        OkHttpUtil.retrofit.create(HomeService.class).getMethodpop(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PopYiEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PopYiEntity popYiEntity) {
                        httpPop.getPopone(popYiEntity);
                    }
                });
    }

    public void getPopRe(String url, String id, final HttpPop httpPop) {
        OkHttpUtil.retrofit.create(HomeService.class).getMethodpopEr(url,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PopErEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PopErEntity popErEntity) {
                           httpPop.getPoptwo(popErEntity);
                    }
                });


    }

    public interface HttpPop {
        void getPopone(PopYiEntity anem);

        void getPoptwo(PopErEntity anme);
    }
}
