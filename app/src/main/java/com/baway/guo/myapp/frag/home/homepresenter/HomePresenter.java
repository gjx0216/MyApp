package com.baway.guo.myapp.frag.home.homepresenter;

import com.baway.guo.myapp.frag.home.entity.BannerEntity;
import com.baway.guo.myapp.frag.home.entity.HomeEntity;
import com.baway.guo.myapp.frag.home.homeView.HomeView;
import com.baway.guo.myapp.frag.home.homemodel.HomeModel;
import com.baway.guo.myapp.net.httpmvp.HttpListener;

public class HomePresenter {

    private HomeView mHomeView;

    private HomeModel mHomeModel;

    public HomePresenter(HomeView homeView) {
        mHomeView = homeView;
        mHomeModel = new HomeModel();
    }

    public void getBanner(String url) {
        mHomeModel.getBanner(url, new HttpListener() {
            @Override
            public void BannerHttpSuccess(BannerEntity name) {
                if (name != null) {
                    mHomeView.htSuccess(name);
                } else {
                    mHomeView.httpFaiuere("失败");
                }
            }

            @Override
            public void HomeHttpSuccess(HomeEntity name) {

            }

            @Override
            public void zhuLogin(Object msg) {

            }

            @Override
            public void zhuHttpFaiure(String msg) {

            }
        });
    }

    public void getHome(String url) {
        mHomeModel.getHome(url, new HttpListener() {
            @Override
            public void BannerHttpSuccess(BannerEntity name) {

            }

            @Override
            public void HomeHttpSuccess(HomeEntity name) {
                if (name != null) {
                    mHomeView.htSuccess1(name);
                } else {
                    mHomeView.httpFaiuere("失败");
                }
            }

            @Override
            public void zhuLogin(Object msg) {

            }

            @Override
            public void zhuHttpFaiure(String msg) {

            }
        });
    }
}
