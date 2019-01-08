package com.baway.guo.myapp.frag.home.homeView;

import com.baway.guo.myapp.frag.home.entity.BannerEntity;
import com.baway.guo.myapp.frag.home.entity.HomeEntity;

public interface HomeView {
    void htSuccess(BannerEntity name);

    void htSuccess1(HomeEntity name);

    void httpFaiuere(String msg);
}
