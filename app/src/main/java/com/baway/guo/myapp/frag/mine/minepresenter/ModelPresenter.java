package com.baway.guo.myapp.frag.mine.minepresenter;

import com.baway.guo.myapp.frag.mine.callback.HttpCallBack;
import com.baway.guo.myapp.frag.mine.minemodel.MinieModel;
import com.baway.guo.myapp.frag.mine.mineview.MineViewe;

public class ModelPresenter {
    private MineViewe mMineView;
    private MinieModel mMinieModel;

    public ModelPresenter(MineViewe mineView) {
        mMineView = mineView;
        mMinieModel = new MinieModel();
    }

    //修改昵称
    public void UpNickName(String url, String nickName) {
        mMinieModel.UpdNickname(url, nickName, new HttpCallBack() {
            @Override
            public void HttpSuccess(String name) {
                if (name != null) {
                    mMineView.htSuccess(name);
                } else {
                    mMineView.httpFaiuere("失败");
                }
            }

            @Override
            public void HttpFaiure(String msg) {

            }
        });
    }

    //修改密码
    public void Uppwd(String url, String oldPwd, String newPwd) {
        mMinieModel.UpPwd(url, oldPwd, newPwd, new HttpCallBack() {
            @Override
            public void HttpSuccess(String name) {
                if (name != null) {
                    mMineView.htSuccess(name);
                } else {
                    mMineView.httpFaiuere("失败");
                }
            }

            @Override
            public void HttpFaiure(String msg) {

            }
        });
    }

    //展示圈子列表
    public void getCriclel(String url) {
        mMinieModel.getCricle(url, new HttpCallBack() {
            @Override
            public void HttpSuccess(String name) {
                if (name != null) {
                    mMineView.htSuccess(name);
                } else {
                    mMineView.httpFaiuere("失败");
                }
            }

            @Override
            public void HttpFaiure(String msg) {

            }
        });
    }

    //删除圈子
    public void getCricleldel(String url) {
        mMinieModel.getCricledel(url, new HttpCallBack() {
            @Override
            public void HttpSuccess(String name) {
                if (name != null) {
                    mMineView.htSuccess(name);
                } else {
                    mMineView.httpFaiuere("失败");
                }
            }

            @Override
            public void HttpFaiure(String msg) {

            }
        });
    }

}
