package com.baway.guo.myapp.frag.mine.frag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.frag.circle.bean.AddEntity;
import com.baway.guo.myapp.frag.mine.event.UpdateEvent;
import com.baway.guo.myapp.frag.mine.minepresenter.ModelPresenter;
import com.baway.guo.myapp.frag.mine.mineview.MineViewe;
import com.baway.guo.myapp.net.Constant;
import com.baway.guo.myapp.util.ToastUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPersonUpdateFragment extends Fragment implements MineViewe {


    @BindView(R.id.name)
    EditText mName;
    @BindView(R.id.updatePerson)
    Button mUpdatePerson;
    private View view;
    private Unbinder unbinder;
    private String mS;
    private ModelPresenter mModelPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_person_update, container, false);
        unbinder = ButterKnife.bind(this, view);
        mModelPresenter = new ModelPresenter(this);
        mUpdatePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mS = mName.getText().toString().trim();
                mModelPresenter.UpNickName(Constant.UPDATENICEHNG, mS);
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
    public void httpSuccess(Object data) {

    }

    @Override
    public void htSuccess(String name) {
        Gson gson = new Gson();
        AddEntity addEntity = gson.fromJson(name, AddEntity.class);
        String status = addEntity.getStatus();
        if (status.equals("0000")) {
            ToastUtil.ToastUtil("修改昵称成功");
            EventBus.getDefault().post(new UpdateEvent(mS));
        } else {
            ToastUtil.ToastUtil("修改昵称失败");
        }
    }

    @Override
    public void htSuccess1(String name) {

    }

    @Override
    public void httpFaiuere(String msg) {

    }
}
