package com.baway.guo.myapp.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.frag.circle.frag.CircleFragment;
import com.baway.guo.myapp.frag.home.event.Addevent;
import com.baway.guo.myapp.frag.home.event.DismissEvent;
import com.baway.guo.myapp.frag.home.frag.HomeFragmentII;
import com.baway.guo.myapp.frag.order.LinFragment;
import com.baway.guo.myapp.frag.mine.frag.MineFragment;
import com.baway.guo.myapp.frag.shop.frag.ShopFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ViewPager viewPager;
    private RadioGroup group;
    private List<Fragment> list;
    private FragmentManager mManager;
    private Fragment mCurFragment;
    private List<Fragment> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }
    private void initView() {
        //找控件
        viewPager = findViewById(R.id.pager);
        group = findViewById(R.id.group);
    }

    private void initData() {

        //新建集合
        list = new ArrayList<>();
        //添加
        list.add(new HomeFragmentII());
        list.add(new CircleFragment());
        list.add(new ShopFragment());
        list.add(new LinFragment());
        list.add(new MineFragment());
        //添加适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        //设置滑动监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                group.check(group.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //单侧的预加载的数量   有5个按钮则要预加载4   左右预加载4页
        viewPager.setOffscreenPageLimit(4);
        //设置点击
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbdio1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rbdio2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rbdio3:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.rbdio4:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.rbdio5:
                        viewPager.setCurrentItem(4);
                        break;
                }
            }
        });
        mManager = getSupportFragmentManager();
    }

   /* //onResume
    @Override
    protected void onResume() {
        int id = getIntent().getIntExtra("id", -1);
        if (id == 3) {
            viewPager.setCurrentItem(3);
            Intent i = new Intent();
            i.setClass(MainActivity.this, LinFragment.class);
            i.putExtra("id", 3);
        }
        super.onResume();
    }*/

    //Eventbus置换Fragment
    @Subscribe
    public void eventAdd(Addevent event) {
        if (viewPager.getVisibility() != View.GONE) {
            viewPager.setVisibility(View.GONE);
        }
        FragmentTransaction transaction = mManager.beginTransaction();
        if (mCurFragment != null) {
            transaction.hide(mCurFragment);
        }
        mCurFragment = event.getAddFragment();
        mList.add(mCurFragment);
        transaction.add(R.id.fragment, mCurFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Subscribe
    public void eventDismiss(DismissEvent event) {
        Fragment needDismiss = event.getDismissFragment();
        mList.remove(needDismiss);
        mCurFragment = null;
        if (mList.size() > 0) {
            mCurFragment = mList.get(mList.size() - 1);
        } else if (viewPager.getVisibility() != View.VISIBLE) {
            viewPager.setVisibility(View.VISIBLE);
        }
        mManager.popBackStack();
    }

    private void clearALL(int position) {
        for (int i = 0; i < mList.size(); i++) {
            mManager.popBackStack();
        }
        mCurFragment = null;
        mList.clear();
        viewPager.setVisibility(View.VISIBLE);
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mList.remove(mCurFragment);
        mCurFragment = null;
        if (mList.size() > 0) {
            mCurFragment = mList.get(mList.size() - 1);
        } else if (viewPager.getVisibility() != View.VISIBLE) {
            viewPager.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
