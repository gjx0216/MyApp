package com.baway.guo.myapp.frag.home.homeView;

import com.baway.guo.myapp.frag.home.entity.HomeSouEntity;

public interface SouView {

    void onSuccess(HomeSouEntity result);

    void onFailer(String msg);
}
