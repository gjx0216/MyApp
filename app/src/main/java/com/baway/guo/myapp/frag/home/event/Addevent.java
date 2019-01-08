package com.baway.guo.myapp.frag.home.event;

import android.support.v4.app.Fragment;

public class Addevent {
    private Fragment addFragment;

    public Addevent(Fragment addFragment) {
        this.addFragment = addFragment;
    }

    public Fragment getAddFragment() {
        return addFragment;
    }
}
