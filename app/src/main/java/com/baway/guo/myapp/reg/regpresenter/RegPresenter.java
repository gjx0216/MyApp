package com.baway.guo.myapp.reg.regpresenter;

import com.baway.guo.myapp.frag.home.entity.BannerEntity;
import com.baway.guo.myapp.frag.home.entity.HomeEntity;
import com.baway.guo.myapp.net.httpmvp.HttpListener;
import com.baway.guo.myapp.net.httpmvp.HttpView;
import com.baway.guo.myapp.reg.regmodel.Regmodel;

public class RegPresenter {

    private HttpView mHttpView;
    private Regmodel mRegmodel;

    public RegPresenter(HttpView httpView) {
        this.mHttpView = httpView;
        mRegmodel = new Regmodel();
    }

    public void postMethod0(String phone, String pwd, String url) {
        mRegmodel.postMethod0(phone, pwd, url, new HttpListener() {

            @Override
            public void BannerHttpSuccess(BannerEntity name) {

            }

            @Override
            public void HomeHttpSuccess(HomeEntity name) {

            }

            @Override
            public void zhuLogin(Object msg) {
                if (msg != null) {
                    mHttpView.httpSuccess(msg);
                } else {
                    mHttpView.httpFaiuere("失败");
                }
            }

            @Override
            public void zhuHttpFaiure(String msg) {

            }
        });
    }

    public void onDestroy() {
        if (mHttpView != null) {
            mHttpView = null;
        }
    }


}
