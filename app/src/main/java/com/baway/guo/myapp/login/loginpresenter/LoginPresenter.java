package com.baway.guo.myapp.login.loginpresenter;

import com.baway.guo.myapp.frag.home.entity.BannerEntity;
import com.baway.guo.myapp.frag.home.entity.HomeEntity;
import com.baway.guo.myapp.login.loginmodel.LoginModel;
import com.baway.guo.myapp.net.httpmvp.HttpListener;
import com.baway.guo.myapp.net.httpmvp.HttpView;

public class LoginPresenter {
    private LoginModel mLoginModel;
    private HttpView mHttpView;

    public LoginPresenter(HttpView httpView) {
        this.mHttpView = httpView;
        mLoginModel = new LoginModel();
    }

    public void postMethod(String phone, String pwd, String url) {
        mLoginModel.postMethod(phone, pwd, url, new HttpListener() {


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
