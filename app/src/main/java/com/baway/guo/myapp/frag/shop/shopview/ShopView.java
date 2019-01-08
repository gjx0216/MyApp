package com.baway.guo.myapp.frag.shop.shopview;

import com.baway.guo.myapp.frag.shop.entity.QuerygoucarEntity;

/**
 * 郭佳兴
 **/
public interface ShopView {


    void onQuerySuccess(QuerygoucarEntity result);

    void onFailer(String url);
}
