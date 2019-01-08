package com.baway.guo.myapp.frag.shop.frag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.frag.home.homeadapter.MoliAdapter;
import com.baway.guo.myapp.frag.shop.adapter.QueryAdapter;
import com.baway.guo.myapp.frag.shop.addsubview.AddSubView;
import com.baway.guo.myapp.frag.shop.entity.QuerygoucarEntity;
import com.baway.guo.myapp.frag.shop.shopPresenter.ShopPresenter;
import com.baway.guo.myapp.frag.shop.shopview.ShopView;
import com.baway.guo.myapp.net.Constant;
import com.baway.guo.myapp.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment implements ShopView {


    @BindView(R.id.QuerygouCarView)
    RecyclerView mShopCarRecycle;
    @BindView(R.id.quanxuan)
    CheckBox mQuanxuan;
    @BindView(R.id.heji)
    TextView mHeji;
    @BindView(R.id.jiesuan)
    Button mJiesuan;
    private View view;
    private Unbinder unbinder;
    private List<QuerygoucarEntity.ResultBean> mMlist;
    private List<QuerygoucarEntity.ResultBean> result = new ArrayList<>();
    private ShopPresenter mShopPresenter;
    private QueryAdapter mQueryAdapter;
    private double total = 0;
    private int numm = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        unbinder = ButterKnife.bind(this, view);
        mQueryAdapter = new QueryAdapter(getActivity(), null);
        mShopPresenter = new ShopPresenter(this);
        mShopPresenter.ShopCar(Constant.QUERYSHOP);
        //全选
        mQuanxuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                initData();
            }
        });
        //计算总价
        mQueryAdapter.setOnNumChanagerListener(new AddSubView.OnNumChanagerListener() {
            @Override
            public void getOnNumChange(int curNum) {
                getTotal();
                numm++;

            }
        });
        mQueryAdapter.setHttpBox(new QueryAdapter.HttpBox() {
            @Override
            public void getBox(int i) {
                boolean check = mMlist.get(i).isCheck();
                if (!check) {
                    mMlist.get(i).setCheck(true);
                    numm++;
                    getTotal();
                } else {
                    mMlist.get(i).setCheck(false);
                    if (numm > 0) {
                        numm--;
                    }
                    getTotal();
                }
            }
        });
        return view;
    }


    //全选
    private void initData() {
        if (mMlist != null) {
            for (int i = 0; i < mMlist.size(); i++) {
                if (mQuanxuan.isChecked()) {
                    mMlist.get(i).setCheck(true);
                    numm++;
                } else {
                    mMlist.get(i).setCheck(false);
                }
            }
            mQueryAdapter.notifyDataSetChanged();
        }
        getTotal();
    }

    //计算价格
    private void getTotal() {
        if (mMlist != null) {
            for (int i = 0; i < mMlist.size(); i++) {
                boolean check = mMlist.get(i).isCheck();
                if (check) {
                    int price = mMlist.get(i).getPrice();
                    total = total + price * mMlist.get(i).getCount();
                    result.add(mMlist.get(i));
                }
            }
            mQueryAdapter.notifyDataSetChanged();
        }
        mHeji.setText("" + total);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onQuerySuccess(QuerygoucarEntity result) {
        mMlist = result.getResult();
        mQueryAdapter.setList(mMlist);
        mShopCarRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        mShopCarRecycle.setAdapter(mQueryAdapter);

    }

    @Override
    public void onFailer(String url) {
        ToastUtil.ToastUtil("失败");
    }
}
