package com.baway.guo.myapp.frag.home.homepresenter;

import com.baway.guo.myapp.frag.home.entity.AddCarEntity;
import com.baway.guo.myapp.frag.home.entity.DetilEntity;
import com.baway.guo.myapp.frag.home.homeView.DetilView;
import com.baway.guo.myapp.frag.home.homemodel.DetalModel;

import java.util.IdentityHashMap;

/**
 * 郭佳兴
 **/
public class DetalPresenter {

    private DetalModel mDetalModel;
    private DetilView mDetilView;

    public DetalPresenter(DetilView detilView) {
        mDetilView = detilView;
        mDetalModel = new DetalModel();
    }

    public void getDetil(String url) {
        mDetalModel.DetilModel(url, new DetalModel.HttpDetil() {
            @Override
            public void getDetil(DetilEntity name) {
                if (name != null) {
                    mDetilView.onDetilSuccess(name);
                } else {
                    mDetilView.onFailer("失败");
                }
            }

            @Override
            public void getAddCar(AddCarEntity name) {

            }
        });
    }

    public void AddCar(String url, String data) {
        mDetalModel.AddCar(url, data, new DetalModel.HttpDetil() {
            @Override
            public void getDetil(DetilEntity name) {

            }

            @Override
            public void getAddCar(AddCarEntity name) {
                if (name != null) {
                    mDetilView.AddCarSuccess(name);
                } else {
                    mDetilView.onFailer("失败");
                }
            }
        });
    }

    public void onestroy() {
        if (mDetilView != null) {
            mDetilView = null;
        }
    }
}
