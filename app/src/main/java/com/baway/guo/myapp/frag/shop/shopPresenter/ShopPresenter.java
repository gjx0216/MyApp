package com.baway.guo.myapp.frag.shop.shopPresenter;

import com.baway.guo.myapp.frag.shop.entity.QuerygoucarEntity;
import com.baway.guo.myapp.frag.shop.shopmodel.ShopModel;
import com.baway.guo.myapp.frag.shop.shopview.ShopView;

/**
 * 郭佳兴
 **/
public class ShopPresenter {

    private ShopView mShopView;
    private ShopModel mShopModel;

    public ShopPresenter(ShopView shopView) {
        mShopView = shopView;
        mShopModel = new ShopModel();
    }

    public void ShopCar(String url) {
        mShopModel.QueryCar(url, new ShopModel.QueryCar() {
            @Override
            public void getQuery(QuerygoucarEntity name) {
                if (name != null) {
                    mShopView.onQuerySuccess(name);
                } else {
                    mShopView.onFailer("失败");
                }
            }
        });
    }

    public void onDestroy() {
        if (mShopView != null) {
            mShopView = null;
        }
    }
}
