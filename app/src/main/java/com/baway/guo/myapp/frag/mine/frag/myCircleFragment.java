package com.baway.guo.myapp.frag.mine.frag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.frag.circle.bean.AddEntity;
import com.baway.guo.myapp.frag.circle.bean.CricleEntity;
import com.baway.guo.myapp.frag.circle.presenter.CriclePresenter;
import com.baway.guo.myapp.frag.circle.view.CricleView;
import com.baway.guo.myapp.frag.mine.MyCricleAdapter;
import com.baway.guo.myapp.frag.mine.bean.MyCircleEntity;
import com.baway.guo.myapp.frag.mine.minepresenter.ModelPresenter;
import com.baway.guo.myapp.frag.mine.mineview.MineViewe;
import com.baway.guo.myapp.net.Constant;
import com.baway.guo.myapp.util.ToastUtil;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class myCircleFragment extends Fragment implements MineViewe, CricleView {


    @BindView(R.id.mycircledelete)
    ImageView mMycircledelete;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.MyCilcleRecycleView)
    RecyclerView mMyCilcleRecycleView;
    private View view;
    private Unbinder unbinder;
    private MyCricleAdapter mMyCricleAdapter;
    private ModelPresenter mModelPresenter;
    private List<MyCircleEntity.ResultBean> mList;
    private CriclePresenter mCriclePresenter;
    private int mId;
    private boolean mIsbutton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_circle, container, false);
        unbinder = ButterKnife.bind(this, view);
        mMyCricleAdapter = new MyCricleAdapter(getActivity(), null);
        mModelPresenter = new ModelPresenter(this);
        mCriclePresenter = new CriclePresenter(this);
        mModelPresenter.getCriclel("small/circle/verify/v1/findMyCircleById?page=1&count=8");
        return view;
    }

    @OnClick({R.id.mycircledelete, R.id.toolBar, R.id.MyCilcleRecycleView})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.mycircledelete:
                //删除
                if (!mIsbutton) {
                    ToastUtil.ToastUtil("删除成功");
                    mCriclePresenter.Quzan(Constant.SHANCHUWOCIRCLE, mId);
                }
                break;
            case R.id.toolBar:
                break;
            case R.id.MyCilcleRecycleView:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void httpSuccess(Object data) {

    }

    @Override
    public void htSuccess(String name) {
        Gson gson = new Gson();
        MyCircleEntity myCircleEntity = gson.fromJson(name, MyCircleEntity.class);
        mList = myCircleEntity.getResult();
        mMyCilcleRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMyCricleAdapter.setBeans(mList);
        mMyCilcleRecycleView.setAdapter(mMyCricleAdapter);
        //点赞
        mMyCricleAdapter.setDianjiListener(new MyCricleAdapter.DianjiListener() {
            @Override
            public void dianji(int po) {
                mCriclePresenter.getZan(Constant.DIANZAN, mList.get(po).getId());
            }
        });
        //取赞
        mMyCricleAdapter.setQuxiaoListener(new MyCricleAdapter.QuxiaoListener() {
            @Override
            public void quxiao(int po) {
                mCriclePresenter.Quzan(Constant.DIANZAN, mList.get(po).getId());
            }
        });
        //删除
        mMyCricleAdapter.setShanchuListener(new MyCricleAdapter.ShanchuListener() {

            @Override
            public void shanchu(int po) {
                mIsbutton = mList.get(po).isIsbutton();
                mId = mList.get(po).getId();
            }
        });
        mMyCricleAdapter.notifyDataSetChanged();
    }


    @Override
    public void htSuccess(CricleEntity name) {

    }

    @Override
    public void htSuccess1(String name) {
        Gson gson = new Gson();
        AddEntity addEntity = gson.fromJson(name, AddEntity.class);
        String status = addEntity.getStatus();
        if (status.equals("0000")) {
            //ToastUtil.toastUtil("成功");
        }
        mMyCricleAdapter.notifyDataSetChanged();
    }

    @Override
    public void httpFaiuere(String msg) {

    }
}
