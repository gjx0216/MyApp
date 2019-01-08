package com.baway.guo.myapp.frag.mine.frag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.frag.home.event.Addevent;
import com.baway.guo.myapp.frag.mine.frag.MyPersonFragment;
import com.baway.guo.myapp.util.SpUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {


    @BindView(R.id.myPersonNiCheng)
    TextView mMyPersonNiCheng;
    @BindView(R.id.myPerson)
    TextView mMyPerson;
    @BindView(R.id.myCircle)
    TextView mMyCircle;
    @BindView(R.id.myFoot)
    TextView mMyFoot;
    @BindView(R.id.myMoney)
    TextView mMyMoney;
    @BindView(R.id.myAddress)
    TextView mMyAddress;
    @BindView(R.id.myPersonIma)
    SimpleDraweeView mMyPersonIma;
    private View view;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        initDta();
        return view;
    }

    private void initDta() {
        //头像
        mMyPersonIma.setImageURI(SpUtil.getString("headPic", ""));
        //昵称
        mMyPersonNiCheng.setText(SpUtil.getString("nickName", ""));
    }

    @OnClick({R.id.myPersonNiCheng, R.id.myPerson, R.id.myCircle, R.id.myFoot, R.id.myMoney, R.id.myAddress, R.id.myPersonIma})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.myPersonNiCheng:
                break;
            case R.id.myPerson:
                EventBus.getDefault().post(new Addevent(new MyPersonFragment()));
                break;
            case R.id.myCircle:
                EventBus.getDefault().post(new Addevent(new myCircleFragment()));
                break;
            case R.id.myFoot:
                break;
            case R.id.myMoney:
                break;
            case R.id.myAddress:
                break;
            case R.id.myPersonIma:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
