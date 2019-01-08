package com.baway.guo.myapp.frag.home.frag;

import android.annotation.SuppressLint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.app.App;
import com.baway.guo.myapp.frag.home.entity.BannerEntity;
import com.baway.guo.myapp.frag.home.entity.HomeEntity;
import com.baway.guo.myapp.frag.home.entity.PopErEntity;
import com.baway.guo.myapp.frag.home.entity.PopYiEntity;
import com.baway.guo.myapp.frag.home.event.Addevent;
import com.baway.guo.myapp.frag.home.homeView.HomeView;
import com.baway.guo.myapp.frag.home.homeView.PopView;
import com.baway.guo.myapp.frag.home.homeadapter.HomeAdapter;
import com.baway.guo.myapp.frag.home.homeadapter.PopErAdapter;
import com.baway.guo.myapp.frag.home.homeadapter.PopYiAdapter;
import com.baway.guo.myapp.frag.home.homepresenter.HomePresenter;
import com.baway.guo.myapp.frag.home.homepresenter.PopPresenter;
import com.baway.guo.myapp.net.Constant;
import com.baway.guo.myapp.util.ToastUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragmentII extends Fragment implements HomeView, PopView {
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @BindView(R.id.zhuImageMenu)
    ImageView mZhuImageMenu;
    @BindView(R.id.zhuImageSearch)
    ImageView mZhuImageSearch;
    @BindView(R.id.homeXrecycle)
    XRecyclerView mHomeXrecycle;
    private View view;
    private Unbinder unbinder;
    private HomeAdapter mHomeAdapter;
    private LinearLayoutManager linearLayoutManager;
    private HomePresenter mHomePresenter;
    private List<BannerEntity.ResultBean> mResult;
    private HomeEntity.ResultBean mList;
    private PopPresenter mPopPresenter;
    private PopYiAdapter mPopYiAdapter;
    private PopErAdapter mPopErAdapter;
    private List<PopYiEntity.ResultBean> mPopYiList;
    private List<PopErEntity.ResultBean> mPopErList;
    private RecyclerView mPopErRecycle;
    private RecyclerView mPopRecyclelerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_fragment_ii, container, false);
        mHomeAdapter = new HomeAdapter(App.context, null, null);
        mPopYiAdapter = new PopYiAdapter(getActivity(), null);
        mPopErAdapter = new PopErAdapter(getActivity(), null);
        unbinder = ButterKnife.bind(this, view);
        mHomePresenter = new HomePresenter(this);
        mHomePresenter.getBanner(Constant.ZHU_BANNER);
        mHomePresenter.getHome(Constant.ZHU_SHOPHTTP);
        mPopPresenter = new PopPresenter(this);
        //加载刷新
        mHomeXrecycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHomeXrecycle.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHomeXrecycle.loadMoreComplete();
                    }
                }, 2000);
            }
        });
        //热销归属
        mHomeAdapter.RexiaoClick(new HomeAdapter.onRexiaoClick() {
            @Override
            public void RexiaoClick() {
                EventBus.getDefault().postSticky(new Addevent(new ReXiaoGuiFragment()));
                //   Toast.makeText(getActivity(),"点了我",Toast.LENGTH_LONG).show();
            }
        });
        //魔力归属
        mHomeAdapter.MoliClick(new HomeAdapter.onMoliClick() {
            @Override
            public void MOliClick() {
                EventBus.getDefault().postSticky(new Addevent(new MoliGuiFragment()));
            }
        });
        //品质归属
        mHomeAdapter.PinnzhiClick(new HomeAdapter.onPinzhiClick() {
            @Override
            public void PinzhiClick() {
                EventBus.getDefault().postSticky(new Addevent(new PinZhiGuiFragment()));
            }
        });
        mZhuImageMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                View pop = View.inflate(getActivity(), R.layout.frag_homemenu, null);
                PopupWindow popupWindow = new PopupWindow(pop, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                mPopRecyclelerView = pop.findViewById(R.id.poprecycleview);
                mPopErRecycle = pop.findViewById(R.id.popErRecycle);
                //避免冲突
                popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                //点击消失
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                //设置位置
                popupWindow.showAsDropDown(pop, 0, -200);
                ///设置网络请求
                mPopPresenter.getPopYi(Constant.YIJI);
            }
        });
        mPopYiAdapter.setPopListener(new PopYiAdapter.PopListener() {
            @Override
            public void poplistener(String popId) {
                mPopPresenter.getPopEr(Constant.ERJI, popId);
            }
        });
        mZhuImageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new Addevent(new MoliGuiFragment()));
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
    public void htSuccess(BannerEntity name) {
        mResult = name.getResult();
        mHomeAdapter.setData(mResult);
        mHomeXrecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeXrecycle.setAdapter(mHomeAdapter);
    }

    @Override
    public void htSuccess1(HomeEntity name) {
        mList = name.getResult();
        mHomeXrecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeAdapter.setMlist(mList);
        mHomeXrecycle.setAdapter(mHomeAdapter);
    }

    @Override
    public void httpFaiuere(String msg) {

    }

    @Override
    public void onPoponeSuccess(PopYiEntity result) {
        mPopYiList = result.getResult();
        mPopYiAdapter.setList(mPopYiList);
        mPopRecyclelerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        mPopRecyclelerView.setAdapter(mPopYiAdapter);
    }

    @Override
    public void onPopTwoSuccess(PopErEntity result) {
        mPopErList = result.getResult();
        mPopErAdapter.setList(mPopErList);
        mPopErRecycle.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
        mPopErRecycle.setAdapter(mPopErAdapter);
    }

    @Override
    public void onFailer(String msg) {
        ToastUtil.ToastUtil("失败");
    }
}
