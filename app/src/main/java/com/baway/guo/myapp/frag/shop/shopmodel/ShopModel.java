package com.baway.guo.myapp.frag.shop.shopmodel;

import com.baway.guo.myapp.frag.shop.entity.QuerygoucarEntity;
import com.baway.guo.myapp.frag.shop.shopservice.ShopService;
import com.baway.guo.myapp.net.OkHttpUtil;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 郭佳兴
 **/
public class ShopModel {

    public void QueryCar(String url, final QueryCar queryCar) {
        OkHttpUtil.retrofit.create(ShopService.class).getQuery(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QuerygoucarEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(QuerygoucarEntity querygoucarEntity) {
                        queryCar.getQuery(querygoucarEntity);
                    }
                });
    }


    public interface QueryCar {
        void getQuery(QuerygoucarEntity name);
    }
}
