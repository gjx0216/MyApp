package com.baway.guo.myapp.frag.home.frag;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.frag.home.entity.HomeSouEntity;
import com.baway.guo.myapp.frag.home.entity.MoliguiEntity;
import com.baway.guo.myapp.frag.home.entity.PinzhiGuiEntity;
import com.baway.guo.myapp.frag.home.entity.RexiaoguiEntity;
import com.baway.guo.myapp.frag.home.homeView.GuiView;
import com.baway.guo.myapp.frag.home.homeView.SouView;
import com.baway.guo.myapp.frag.home.homeadapter.RexiaoGuiAdapter;
import com.baway.guo.myapp.frag.home.homeadapter.SouAdapter;
import com.baway.guo.myapp.frag.home.homepresenter.GuiPresenter;
import com.baway.guo.myapp.frag.home.homepresenter.SouPresenter;
import com.baway.guo.myapp.net.Constant;
import com.baway.guo.myapp.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReXiaoGuiFragment extends Fragment implements GuiView, SouView {


    @BindView(R.id.recycleRexiaogui)
    RecyclerView mRecycleRexiaogui;
    @BindView(R.id.zhuSearchIma)
    ImageView mZhuSearchIma;
    @BindView(R.id.zhuSearchSou)
    EditText mZhuSearchSou;
    @BindView(R.id.zhuSearchSousuo)
    TextView mZhuSearchSousuo;
    @BindView(R.id.sourecycle)
    RecyclerView mSourecycle;
    private View view;
    private Unbinder unbinder;
    private RexiaoGuiAdapter mRexiaoGuiAdapter;
    private GuiPresenter mGuiPresenter;
    private List<RexiaoguiEntity.ResultBean> mList;
    private SouPresenter mSouPresenter;
    private List<HomeSouEntity.ResultBean> mSouList;
    private SouAdapter mSouAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_re_xiao_gui, container, false);
        unbinder = ButterKnife.bind(this, view);
        mGuiPresenter = new GuiPresenter(this);
        mGuiPresenter.getRexiao(Constant.ReXiaoGui);
        mRexiaoGuiAdapter = new RexiaoGuiAdapter(getActivity(), null);
        mSouAdapter = new SouAdapter(getActivity(), null);
        mSouPresenter = new SouPresenter(this);
        return view;
    }

    @Override
    public void onRexiaoSuccess(RexiaoguiEntity result) {
        mList = result.getResult();
        mRexiaoGuiAdapter.setList(mList);
        mRecycleRexiaogui.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecycleRexiaogui.setAdapter(mRexiaoGuiAdapter);
        mRexiaoGuiAdapter.setOnItemClick(new RexiaoGuiAdapter.OnItemClick() {
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
    public void onMoliSuccess(MoliguiEntity result) {

    }

    @Override
    public void onPinzhiSucccess(PinzhiGuiEntity result) {

    }

    @Override
    public void onFarler(String msg) {
        ToastUtil.ToastUtil("失败");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.zhuSearchIma, R.id.zhuSearchSou, R.id.zhuSearchSousuo})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.zhuSearchIma:
                break;
            case R.id.zhuSearchSou:
                break;
            case R.id.zhuSearchSousuo:
                if (mSouList != null) {
                    mRecycleRexiaogui.setVisibility(View.GONE);
                    mSourecycle.setVisibility(View.VISIBLE);
                    String s = mZhuSearchSou.getText().toString();
                    mSouPresenter.getSou(Constant.HomeSou + "?keyword=" + s + "&page=" + 1 + "&count=" + 3);
                } else {
                    //  Toast.makeText(getActivity(), "空空如也", Toast.LENGTH_LONG).show();
                    mRecycleRexiaogui.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void onSuccess(HomeSouEntity result) {
        mSouList = result.getResult();
        mSouAdapter.setList(mSouList);
        mSourecycle.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mSourecycle.setAdapter(mSouAdapter);
        ToastUtil.ToastUtil(mSouList.toString());
    }

    @Override
    public void onFailer(String msg) {
        ToastUtil.ToastUtil("解析失败");
    }
}
