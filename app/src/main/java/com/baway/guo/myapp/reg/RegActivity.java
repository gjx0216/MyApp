package com.baway.guo.myapp.reg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.app.MainActivity;
import com.baway.guo.myapp.frag.home.entity.BannerEntity;
import com.baway.guo.myapp.frag.home.entity.HomeEntity;
import com.baway.guo.myapp.login.LoginActivity;
import com.baway.guo.myapp.net.httpmvp.HttpView;
import com.baway.guo.myapp.reg.regpresenter.RegPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegActivity extends AppCompatActivity implements HttpView {

    @BindView(R.id.regPhone)
    EditText mRegPhone;
    @BindView(R.id.regYanZheng)
    EditText mRegYanZheng;
    @BindView(R.id.regYanHuo)
    TextView mRegYanHuo;
    @BindView(R.id.regPwd)
    EditText mRegPwd;
    @BindView(R.id.regIma)
    ImageView mRegIma;
    @BindView(R.id.loginButton)
    TextView mLoginButton;
    @BindView(R.id.RegButton)
    Button mRegButton;
    private RegPresenter mRegPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        mRegPresenter = new RegPresenter(this);
    }

    @OnClick({R.id.regYanHuo, R.id.regPwd, R.id.regIma, R.id.loginButton, R.id.RegButton})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.regYanHuo:
                break;
            case R.id.regPwd:
                break;
            case R.id.regIma:
                if (mRegPwd.getInputType() == 129) {
                    mRegPwd.setInputType(128);
                } else {
                    mRegPwd.setInputType(129);
                }
                break;
            case R.id.loginButton:
                startActivity(new Intent(RegActivity.this, LoginActivity.class));
                break;
            case R.id.RegButton:
                String phone = mRegPhone.getText().toString();
                String pwd = mRegPwd.getText().toString();
                mRegPresenter.postMethod0(phone, pwd, "small/user/v1/register");
                break;
        }
    }

    @Override
    public void httpSuccess(Object data) {
        startActivity(new Intent(RegActivity.this, MainActivity.class));
    }



    @Override
    public void httpFaiuere(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRegPresenter.onDestroy();
    }
}
