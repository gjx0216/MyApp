package com.baway.guo.myapp.frag.home.homepresenter;

import com.baway.guo.myapp.frag.home.entity.MoliguiEntity;
import com.baway.guo.myapp.frag.home.entity.PinzhiGuiEntity;
import com.baway.guo.myapp.frag.home.entity.RexiaoguiEntity;
import com.baway.guo.myapp.frag.home.homeView.GuiView;
import com.baway.guo.myapp.frag.home.homemodel.GuiModel;

public class GuiPresenter {
    private GuiView mGuiView;
    private GuiModel mGuiModel;

    public GuiPresenter(GuiView guiView) {
        mGuiView = guiView;
        mGuiModel = new GuiModel();
    }

    public void getRexiao(String url) {
        mGuiModel.RexiaoGui(url, new GuiModel.HttpGui() {
            @Override
            public void getRexiao(RexiaoguiEntity rexiao) {
                if (rexiao != null) {
                    mGuiView.onRexiaoSuccess(rexiao);
                } else {
                    mGuiView.onFarler("失败");
                }
            }

            @Override
            public void getMoli(MoliguiEntity moli) {
            }
            @Override
            public void getPinzhi(PinzhiGuiEntity pinzhi) {
                if (pinzhi != null) {
                    mGuiView.onPinzhiSucccess(pinzhi);
                } else {
                    mGuiView.onFarler("失败");
                }
            }
        });
    }
    public void getMoli(String url) {
        mGuiModel.MoliGui(url, new GuiModel.HttpGui() {
            @Override
            public void getRexiao(RexiaoguiEntity rexiao) {
            }

            @Override
            public void getMoli(MoliguiEntity moli) {
                if (moli != null) {
                    mGuiView.onMoliSuccess(moli);
                } else {
                    mGuiView.onFarler("失败");
                }
            }
            @Override
            public void getPinzhi(PinzhiGuiEntity pinzhi) {
            }
        });
    }
    public void getPinzhi(String url) {
        mGuiModel.PinzhiGui(url, new GuiModel.HttpGui() {
            @Override
            public void getRexiao(RexiaoguiEntity rexiao) {
            }

            @Override
            public void getMoli(MoliguiEntity moli) {
            }

            @Override
            public void getPinzhi(PinzhiGuiEntity pinzhi) {
                if (pinzhi != null) {
                    mGuiView.onPinzhiSucccess(pinzhi);
                } else {
                    mGuiView.onFarler("失败");
                }
            }
        });
    }
}
