package com.baway.guo.myapp.frag.mine.minemodel;

import com.baway.guo.myapp.frag.mine.callback.HttpCallBack;
import com.baway.guo.myapp.frag.mine.mineservice.MineService;
import com.baway.guo.myapp.net.OkHttpUtil;
import com.baway.guo.myapp.net.httpmvp.HttpListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MinieModel {

    //修改昵称
    public void UpdNickname(String url, String nickName, final HttpCallBack httpCallBack) {
        OkHttpUtil.retrofit.create(MineService.class).UpdNickname(url, nickName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        httpCallBack.HttpSuccess(s);
                    }
                });

    }

    //修改密码
    public void UpPwd(String url, String oldPwd, String newPwd, final HttpCallBack httpCallBack) {
        OkHttpUtil.retrofit.create(MineService.class).Updpwd(url, oldPwd, newPwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        httpCallBack.HttpSuccess(s);
                    }
                });
    }

    //我的圈子
    public void getCricle(String url, final HttpCallBack httpCallBack) {
        OkHttpUtil.retrofit.create(MineService.class).getCricle(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        httpCallBack.HttpSuccess(s);
                    }
                });
    }

    //删除圈子
    public void getCricledel(String url, final HttpCallBack httpCallBack) {
        OkHttpUtil.retrofit.create(MineService.class).getCricledel(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        httpCallBack.HttpSuccess(s);
                    }
                });

    }
}
