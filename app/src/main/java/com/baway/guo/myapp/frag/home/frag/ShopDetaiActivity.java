package com.baway.guo.myapp.frag.home.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.app.App;
import com.baway.guo.myapp.frag.home.entity.AddCarEntity;
import com.baway.guo.myapp.frag.home.entity.DetilEntity;
import com.baway.guo.myapp.frag.home.homeView.DetilView;
import com.baway.guo.myapp.frag.home.homeadapter.ShopDetilAdapter;
import com.baway.guo.myapp.frag.home.homepresenter.DetalPresenter;
import com.baway.guo.myapp.net.Constant;
import com.baway.guo.myapp.util.ToastUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopDetaiActivity extends AppCompatActivity implements DetilView {

    @BindView(R.id.imageF)
    ImageView mImageF;
    @BindView(R.id.shangpin)
    TextView mShangpin;
    @BindView(R.id.xiangqing)
    TextView mXiangqing;
    @BindView(R.id.pinglun)
    TextView mPinglun;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.liner)
    LinearLayout mLiner;
    @BindView(R.id.zhuSouRecycleView)
    RecyclerView mDetilRecycle;
    @BindView(R.id.car)
    ImageView mCar;
    @BindView(R.id.mai)
    ImageView mMai;

    private ShopDetilAdapter mShopDetilAdapter;
    private DetilEntity.ResultBean mBean;
    private DetalPresenter mDetalPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detai);
        ButterKnife.bind(this);
        mShopDetilAdapter = new ShopDetilAdapter(getApplicationContext(), null);
        mDetalPresenter = new DetalPresenter(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        mDetalPresenter.getDetil("small/commodity/v1/findCommodityDetailsById?commodityId=" + id);


        //添加滑动
        mDetilRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mToolBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            finish();
        }
        return super.onTouchEvent(event);
    }


    @OnClick({R.id.imageF, R.id.shangpin, R.id.xiangqing, R.id.pinglun, R.id.toolBar, R.id.liner, R.id.zhuSouRecycleView, R.id.car, R.id.mai})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            //避免冲突
            case R.id.imageF:
                finish();
                break;
            case R.id.shangpin:
                mDetilRecycle.scrollToPosition(0);
                break;
            case R.id.xiangqing:
                mDetilRecycle.scrollToPosition(2);
                break;
            case R.id.pinglun:
                mDetilRecycle.scrollToPosition(3);
                break;
            case R.id.car:
                AddCarEntity addCarEntity = new AddCarEntity();

                addCarEntity.setCommodityId(mBean.getCommodityId());
                addCarEntity.setCount(1);

                List<AddCarEntity> list = new ArrayList<>();
                list.add(addCarEntity);
                Gson gson = new Gson();
                String s = gson.toJson(list);
                mDetalPresenter.AddCar(Constant.TOBUSHOP, s);
                break;
            case R.id.mai:
                break;
        }
    }

    @Override
    public void onDetilSuccess(DetilEntity result) {
        mBean = result.getResult();
        mDetilRecycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mShopDetilAdapter.setList(mBean);
        mDetilRecycle.setAdapter(mShopDetilAdapter);
    }

    @Override
    public void AddCarSuccess(AddCarEntity result) {
        ToastUtil.ToastUtil("添加成功");
    }

    @Override
    public void onFailer(String msg) {

    }
}
