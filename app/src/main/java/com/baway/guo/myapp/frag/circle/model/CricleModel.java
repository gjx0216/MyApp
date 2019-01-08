package com.baway.guo.myapp.frag.circle.model;

import com.baway.guo.myapp.frag.circle.bean.CricleEntity;
import com.baway.guo.myapp.frag.circle.cricleListener.CricleBack;
import com.baway.guo.myapp.frag.circle.service.CricleService;
import com.baway.guo.myapp.net.OkHttpUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CricleModel {
    //圈子列表
    public void CricleModel(int page, int count, final CricleBack cricleBack) {
        OkHttpUtil.retrofit.create(CricleService.class).getCricle(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Observer<CricleEntity>() {
                     @Override
                     public void onCompleted() {

                     }

                     @Override
                     public void onError(Throwable e) {

                     }

                     @Override
                     public void onNext(CricleEntity cricleEntity) {
                   cricleBack.CricleSuccess(cricleEntity);
                     }
                 });


    }

    //点赞
    public void Dianzan(String url, int circleId, final CricleBack cricleBack) {
        OkHttpUtil.retrofit.create(CricleService.class).getZan(url, circleId).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                cricleBack.CricleSuccess1(body);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                call.cancel();
            }
        });
    }

    //取消点赞
    public void QuZan(String url, int circleId, final CricleBack cricleBack) {
        OkHttpUtil.retrofit.create(CricleService.class).getQuZan(url, circleId).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                cricleBack.CricleSuccess1(body);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
