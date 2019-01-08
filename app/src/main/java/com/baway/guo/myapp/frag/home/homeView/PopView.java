package com.baway.guo.myapp.frag.home.homeView;

import com.baway.guo.myapp.frag.home.entity.PopErEntity;
import com.baway.guo.myapp.frag.home.entity.PopYiEntity;

public interface PopView {
    void onPoponeSuccess(PopYiEntity result);

    void onPopTwoSuccess(PopErEntity result);

    void onFailer(String msg);
}
