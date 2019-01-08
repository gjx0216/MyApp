package com.baway.guo.myapp.frag.circle.view;

import com.baway.guo.myapp.frag.circle.bean.CricleEntity;

public interface CricleView {

    void htSuccess(CricleEntity name);

    //点赞
    void htSuccess1(String name);

    void httpFaiuere(String msg);
}
