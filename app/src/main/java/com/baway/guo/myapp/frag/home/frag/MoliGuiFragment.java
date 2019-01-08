package com.baway.guo.myapp.frag.home.frag;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.frag.home.entity.MoliguiEntity;
import com.baway.guo.myapp.frag.home.entity.PinzhiGuiEntity;
import com.baway.guo.myapp.frag.home.entity.RexiaoguiEntity;
import com.baway.guo.myapp.frag.home.homeView.GuiView;
import com.baway.guo.myapp.frag.home.homeadapter.MoliGuiAdapter;
import com.baway.guo.myapp.frag.home.homepresenter.GuiPresenter;
import com.baway.guo.myapp.net.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoliGuiFragment extends Fragment implements GuiView {


    @BindView(R.id.recycleMoligui)
    RecyclerView mRecycleMoligui;
    private View view;
    private Unbinder unbinder;
    private MoliGuiAdapter mMoliGuiAdapter;
    private GuiPresenter mGuiPresenter;
    private List<MoliguiEntity.ResultBean> mList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moli_gui, container, false);
        unbinder = ButterKnife.bind(this, view);
        mMoliGuiAdapter = new MoliGuiAdapter(getActivity(), null);
        mGuiPresenter = new GuiPresenter(this);
        mGuiPresenter.getMoli(Constant.MoLiGui);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRexiaoSuccess(RexiaoguiEntity result) {

    }

    @Override
    public void onMoliSuccess(MoliguiEntity result) {
        mList = result.getResult();
        mMoliGuiAdapter.setList(mList);
        mRecycleMoligui.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecycleMoligui.setAdapter(mMoliGuiAdapter);

        mMoliGuiAdapter.setOnItemClick(new MoliGuiAdapter.OnItemClick() {
            @Override
            public void onItemClik(View view, int i) {
                // int id = mList.get(i).getCommodityId();
                Intent intent = new Intent(getActivity(), ShopDetaiActivity.class);
                intent.putExtra("id", String.valueOf(mList.get(i).getCommodityId()));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPinzhiSucccess(PinzhiGuiEntity result) {

    }

    @Override
    public void onFarler(String msg) {

    }
}
