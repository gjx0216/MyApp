package com.baway.guo.myapp.frag.home.homepresenter;

import com.baway.guo.myapp.frag.home.entity.HomeSouEntity;
import com.baway.guo.myapp.frag.home.homeView.SouView;
import com.baway.guo.myapp.frag.home.homemodel.SouModel;

public class SouPresenter {
    private SouView mSouView;
    private SouModel mSouModel;

    public SouPresenter(SouView souView) {
        mSouView = souView;
        mSouModel = new SouModel();
    }

    public void getSou(String url) {
        mSouModel.SouModel(url, new SouModel.HttpSou() {
            @Override
            public void getSou(HomeSouEntity name) {
                if (name != null) {
                    mSouView.onSuccess(name);
                } else {
                    mSouView.onFailer("失败");
                }
            }
        });
    }
}
