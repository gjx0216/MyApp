package com.baway.guo.myapp.frag.circle.presenter;

import com.baway.guo.myapp.frag.circle.bean.CricleEntity;
import com.baway.guo.myapp.frag.circle.cricleListener.CricleBack;
import com.baway.guo.myapp.frag.circle.model.CricleModel;
import com.baway.guo.myapp.frag.circle.view.CricleView;
import com.baway.guo.myapp.util.ToastUtil;

public class CriclePresenter {
    private CricleView mCricleView;
    private CricleModel mCricleModel;

    public CriclePresenter(CricleView cricleView) {
        mCricleView = cricleView;
        mCricleModel = new CricleModel();
    }

    public void getCricle(int page, int count) {
        mCricleModel.CricleModel(page, count, new CricleBack() {
            @Override
            public void CricleSuccess(CricleEntity name) {
                if (name != null) {
                    mCricleView.htSuccess(name);
                } else {
                    mCricleView.httpFaiuere("失败");
                }
            }

            @Override
            public void CricleSuccess1(String name) {

            }

            @Override
            public void onFaiure(String msg) {
                ToastUtil.ToastUtil("失败");
            }
        });
    }

    public void getZan(String url, int circleId) {
        mCricleModel.Dianzan(url, circleId, new CricleBack() {
            @Override
            public void CricleSuccess(CricleEntity name) {

            }

            @Override
            public void CricleSuccess1(String name) {
                if (name != null) {
                    mCricleView.htSuccess1(name);
                } else {
                    mCricleView.httpFaiuere("失败");
                }
            }

            @Override
            public void onFaiure(String msg) {

            }
        });
    }

    public void Quzan(String url, int circleId) {
        mCricleModel.QuZan(url, circleId, new CricleBack() {
            @Override
            public void CricleSuccess(CricleEntity name) {

            }

            @Override
            public void CricleSuccess1(String name) {
                if (name != null) {
                    mCricleView.htSuccess1(name);
                } else {
                    mCricleView.httpFaiuere("失败");
                }
            }

            @Override
            public void onFaiure(String msg) {

            }
        });
    }
}
