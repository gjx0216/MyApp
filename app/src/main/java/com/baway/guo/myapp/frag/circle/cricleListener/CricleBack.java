package com.baway.guo.myapp.frag.circle.cricleListener;

import com.baway.guo.myapp.frag.circle.bean.CricleEntity;

public interface CricleBack {
    void CricleSuccess(CricleEntity name);

    void CricleSuccess1(String name);

    void onFaiure(String msg);
}
