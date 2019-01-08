package com.baway.guo.myapp.frag.home.homemodel;

import com.baway.guo.myapp.frag.home.entity.MoliguiEntity;
import com.baway.guo.myapp.frag.home.entity.PinzhiGuiEntity;
import com.baway.guo.myapp.frag.home.entity.RexiaoguiEntity;
import com.baway.guo.myapp.frag.home.homeservice.HomeService;
import com.baway.guo.myapp.net.OkHttpUtil;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GuiModel {

    public void RexiaoGui(String url, final HttpGui httpGui) {
        OkHttpUtil.retrofit.create(HomeService.class).getRexiao(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RexiaoguiEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RexiaoguiEntity rexiaoguiEntity) {
                        httpGui.getRexiao(rexiaoguiEntity);
                    }
                });
    }

    public void MoliGui(String url, final HttpGui httpGui) {
        OkHttpUtil.retrofit.create(HomeService.class).getMoli(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoliguiEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MoliguiEntity moliguiEntity) {
                        httpGui.getMoli(moliguiEntity);
                    }
                });
    }

    public void PinzhiGui(String url, final HttpGui httpGui) {
        OkHttpUtil.retrofit.create(HomeService.class).getPinzhi(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PinzhiGuiEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PinzhiGuiEntity pinzhiGuiEntity) {
                        httpGui.getPinzhi(pinzhiGuiEntity);
                    }
                });
    }

    public interface HttpGui {
        void getRexiao(RexiaoguiEntity rexiao);

        void getMoli(MoliguiEntity moli);

        void getPinzhi(PinzhiGuiEntity pinzhi);
    }
}
