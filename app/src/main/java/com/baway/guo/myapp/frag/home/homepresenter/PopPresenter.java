package com.baway.guo.myapp.frag.home.homepresenter;

import com.baway.guo.myapp.frag.home.entity.PopErEntity;
import com.baway.guo.myapp.frag.home.entity.PopYiEntity;
import com.baway.guo.myapp.frag.home.homeView.PopView;
import com.baway.guo.myapp.frag.home.homemodel.PopModel;

public class PopPresenter {
    private PopView mPopView;
    private PopModel mPopModel;

    public PopPresenter(PopView popView) {
        mPopView = popView;
        mPopModel = new PopModel();
    }

    public void getPopYi(String url) {
        mPopModel.getPopyi(url, new PopModel.HttpPop() {
            @Override
            public void getPopone(PopYiEntity anem) {
                if (anem != null) {
                    mPopView.onPoponeSuccess(anem);
                } else {
                    mPopView.onFailer("失败");
                }
            }

            @Override
            public void getPoptwo(PopErEntity anme) {

            }
        });
    }

    public void getPopEr(String url, String id) {
        mPopModel.getPopRe(url, id, new PopModel.HttpPop() {
            @Override
            public void getPopone(PopYiEntity anem) {

            }

            @Override
            public void getPoptwo(PopErEntity anme) {
                if (anme != null) {
                    mPopView.onPopTwoSuccess(anme);
                } else {
                    mPopView.onFailer("失败");
                }
            }
        });
    }
}
