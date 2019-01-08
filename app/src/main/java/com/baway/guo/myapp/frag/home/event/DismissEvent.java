package com.baway.guo.myapp.frag.home.event;

import android.support.v4.app.Fragment;

public class DismissEvent {

    private Fragment DismissFragment;

    public DismissEvent(Fragment dismissFragment) {
        this.DismissFragment = dismissFragment;
    }

    public Fragment getDismissFragment() {
        return DismissFragment;
    }
}
