package com.baway.guo.myapp.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.guo.myapp.app.MainActivity;
import com.baway.guo.myapp.R;
import com.baway.guo.myapp.frag.home.entity.BannerEntity;
import com.baway.guo.myapp.frag.home.entity.HomeEntity;
import com.baway.guo.myapp.login.loginpresenter.LoginPresenter;
import com.baway.guo.myapp.net.httpmvp.HttpView;
import com.baway.guo.myapp.reg.RegActivity;
import com.baway.guo.myapp.util.SpUtil;
import com.baway.guo.myapp.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements HttpView {


    @BindView(R.id.loginPhone)
    EditText mLoginPhone;
    @BindView(R.id.loginPwd)
    EditText mLoginPwd;
    @BindView(R.id.loginIma)
    ImageView mLoginIma;
    @BindView(R.id.loginCk)
    CheckBox mLoginCk;
    @BindView(R.id.loginReg)
    TextView mLoginReg;
    @BindView(R.id.loginButton)
    Button mLoginButton;

    private LoginPresenter mLoginPresenter;
    private SharedPreferences mSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenter(this);
        mSp = getSharedPreferences("user", Context.MODE_PRIVATE);

        //第二次进入
        if (mSp.getBoolean("记住密码", false)) {
            String phone = mSp.getString("phone", "");
            String pwd = mSp.getString("pwd", "");
            // String pwd = sp.getString("pwd", "");
            boolean b = mSp.getBoolean("记住密码", false);
            mLoginPhone.setText(phone);
            mLoginPwd.setText(pwd);
            //  loginPwd.setText(pwd);
            mLoginCk.setChecked(b);
        }

    }


    @OnClick({R.id.loginIma, R.id.loginCk, R.id.loginReg, R.id.loginButton})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.loginIma:
                //129是密码不可见(隐藏密码) 128是密码可见(明文密码)
                if (mLoginPwd.getInputType() == 129) {
                    mLoginPwd.setInputType(128);
                } else {
                    mLoginPwd.setInputType(129);
                }
                break;
            case R.id.loginCk:
                break;
            case R.id.loginReg:
                startActivity(new Intent(getApplicationContext(), RegActivity.class));
                break;
            case R.id.loginButton:
                String phone = mLoginPhone.getText().toString();
                String pwd = mLoginPwd.getText().toString();

                //存值
                SpUtil.put("pwd", pwd);
                mLoginPresenter.postMethod(phone, pwd, "small/user/v1/login");
                SharedPreferences.Editor edit = mSp.edit();
                if (mLoginCk.isChecked()) {
                    edit.putString("phone", phone);
                    edit.putString("pwd", pwd);
                    edit.putBoolean("记住密码", true);
                    edit.commit();
                }
                break;
        }
    }

    @Override
    public void httpSuccess(Object data) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }


    @Override
    public void httpFaiuere(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.onDestroy();
    }
}
