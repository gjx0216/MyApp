package com.baway.guo.myapp.frag.mine.frag;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.frag.home.event.Addevent;
import com.baway.guo.myapp.frag.mine.event.UpdateEvent;
import com.baway.guo.myapp.util.SpUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPersonFragment extends Fragment {


    @BindView(R.id.myPersonImage)
    SimpleDraweeView mMyPersonImage;
    @BindView(R.id.myPersonName)
    EditText mMyPersonName;
    @BindView(R.id.myPersonPwd)
    EditText mMyPersonPwd;
    private View view;
    private Unbinder unbinder;

    @Override
    public void onStart() {
        super.onStart();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().isRegistered(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_person, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();

        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventName(UpdateEvent u) {
        mMyPersonName.setText(u.getName());
    }

    private void initData() {
        mMyPersonImage.setImageURI(Uri.parse(SpUtil.getString("headPic", "")));
        mMyPersonName.setText(SpUtil.getString("nickName", ""));
        mMyPersonPwd.setText(SpUtil.getString("pwd", ""));
    }



    @OnClick({R.id.myPersonImage, R.id.myPersonName, R.id.myPersonPwd})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.myPersonImage:
                break;
            case R.id.myPersonName:
                EventBus.getDefault().post(new Addevent(new MyPersonUpdateFragment()));
                break;
            case R.id.myPersonPwd:
                EventBus.getDefault().post(new Addevent(new MyPersonUpPwdFragment()));
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
