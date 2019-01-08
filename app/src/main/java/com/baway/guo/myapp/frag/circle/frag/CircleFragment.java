package com.baway.guo.myapp.frag.circle.frag;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.frag.circle.adapter.CricleAdapter;
import com.baway.guo.myapp.frag.circle.bean.AddEntity;
import com.baway.guo.myapp.frag.circle.bean.CricleEntity;
import com.baway.guo.myapp.frag.circle.presenter.CriclePresenter;
import com.baway.guo.myapp.frag.circle.view.CricleView;
import com.baway.guo.myapp.net.Constant;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircleFragment extends Fragment implements CricleView {

    @BindView(R.id.CircleRecycleView)
    XRecyclerView mCircleRecycleView;
    private View view;
    private Unbinder unbinder;
    private CricleAdapter mCricleAdapter;
    private List<CricleEntity.ResultBean> list = new ArrayList<>();
    private CriclePresenter mCriclePresenter;
    private int page = 1;
    private int count = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circle, container, false);
        unbinder = ButterKnife.bind(this, view);
        mCricleAdapter = new CricleAdapter(getActivity(), null);
        mCriclePresenter = new CriclePresenter(this);
        mCriclePresenter.getCricle(page, count);
        //下拉刷新,上拉加载
        mCircleRecycleView.setPullRefreshEnabled(true);
        mCircleRecycleView.setLoadingMoreEnabled(true);
        mCircleRecycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        mCriclePresenter.getCricle(page, count);
                        mCircleRecycleView.refreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        count++;
                        mCriclePresenter.getCricle(page, count);
                        mCircleRecycleView.loadMoreComplete();
                    }
                }, 1000);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void htSuccess(CricleEntity name) {
      /*  Gson gson = new Gson();
        CricleEntity cricleEntity = gson.fromJson(name, CricleEntity.class);*/

        list.addAll(name.getResult());
        mCircleRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter();
        //点赞接口路径
        mCricleAdapter.setDianjiListener(new CricleAdapter.DianjiListener() {
            @Override
            public void dianji(int po) {
                mCriclePresenter.getZan(Constant.DIANZAN, list.get(po).getId());
            }
        });
        //取消点赞
        mCricleAdapter.setQuxiaoListener(new CricleAdapter.QuxiaoListener() {
            @Override
            public void quxiao(int po) {
                mCriclePresenter.Quzan(Constant.QUXIAO, list.get(po).getId());
            }
        });

    }

    @Override
    public void htSuccess1(String name) {
        Gson gson = new Gson();
        AddEntity addEntity = gson.fromJson(name, AddEntity.class);
        String status = addEntity.getStatus();
        if (status.equals("0000")) {
            //  ToastUtil.toastUtil("成功");
        } else {
            // ToastUtil.toastUtil("失败");
        }
    }

    private void adapter() {
        if (mCricleAdapter == null) {
            mCricleAdapter = new CricleAdapter(getActivity(), null);
            mCircleRecycleView.setAdapter(mCricleAdapter);
            mCricleAdapter.setList(list);
        } else {
            mCircleRecycleView.setAdapter(mCricleAdapter);
            mCricleAdapter.setList(list);
            mCricleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void httpFaiuere(String msg) {

    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
}
