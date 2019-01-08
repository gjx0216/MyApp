package com.baway.guo.myapp.frag.home.homeView;

import com.baway.guo.myapp.frag.home.entity.MoliguiEntity;
import com.baway.guo.myapp.frag.home.entity.PinzhiGuiEntity;
import com.baway.guo.myapp.frag.home.entity.RexiaoguiEntity;

public interface GuiView {
    void onRexiaoSuccess(RexiaoguiEntity result);

    void onMoliSuccess(MoliguiEntity result);

    void onPinzhiSucccess(PinzhiGuiEntity result);

    void onFarler(String msg);
}
