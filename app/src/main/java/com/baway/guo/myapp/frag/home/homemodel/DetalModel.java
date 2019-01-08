package com.baway.guo.myapp.frag.home.homemodel;

import com.baway.guo.myapp.frag.home.entity.AddCarEntity;
import com.baway.guo.myapp.frag.home.entity.DetilEntity;
import com.baway.guo.myapp.frag.home.entity.HomeSouEntity;
import com.baway.guo.myapp.frag.home.homeservice.HomeService;
import com.baway.guo.myapp.net.OkHttpUtil;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.os.Build.VERSION_CODES.O;

/**
 * 郭佳兴
 **/
public class DetalModel {
    //详情
    public void DetilModel(String url, final HttpDetil httpDetil) {
        OkHttpUtil.retrofit.create(HomeService.class).getDetil(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetilEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DetilEntity detilEntity) {
                        httpDetil.getDetil(detilEntity);
                    }
                });
    }

    //同步购物车
    public void AddCar(String url, String data, final HttpDetil httpDetil) {
        OkHttpUtil.retrofit.create(HomeService.class).AddCar(url, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddCarEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AddCarEntity addCarEntity) {
                        httpDetil.getAddCar(addCarEntity);
                    }
                });
    }

    public interface HttpDetil {
        void getDetil(DetilEntity name);

        void getAddCar(AddCarEntity name);
    }
}
