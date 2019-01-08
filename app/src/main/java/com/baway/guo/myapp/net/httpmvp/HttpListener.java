package com.baway.guo.myapp.net.httpmvp;

import com.baway.guo.myapp.frag.home.entity.BannerEntity;
import com.baway.guo.myapp.frag.home.entity.HomeEntity;

public interface HttpListener<T> {
    void BannerHttpSuccess(BannerEntity name);

    void HomeHttpSuccess(HomeEntity name);

    void zhuLogin(T msg);

    void zhuHttpFaiure(String msg);
}
