package com.baway.guo.myapp.frag.home.homeView;

import com.baway.guo.myapp.frag.home.entity.AddCarEntity;
import com.baway.guo.myapp.frag.home.entity.DetilEntity;

/**
 * 郭佳兴
 **/
public interface DetilView {

    void onDetilSuccess(DetilEntity result);

    void AddCarSuccess(AddCarEntity result);

    void onFailer(String msg);

}
