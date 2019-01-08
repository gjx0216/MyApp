package com.baway.guo.myapp.login.loginmodel;

import android.util.Log;

import com.baway.guo.myapp.login.loginentity.LoginEntity;
import com.baway.guo.myapp.login.loginretrofit.LoginRetrofitApi;
import com.baway.guo.myapp.net.httpmvp.HttpListener;
import com.baway.guo.myapp.net.OkHttpUtil;
import com.baway.guo.myapp.util.SpUtil;
import com.baway.guo.myapp.util.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel {
    //登录
    public void postMethod(String phone, String pwd, String url, final HttpListener httpListener) {
        LoginRetrofitApi retrofitApi = OkHttpUtil.retrofit.create(LoginRetrofitApi.class);
        Call<LoginEntity> objectCall = retrofitApi.postMethod(phone, pwd, url);
        objectCall.enqueue(new Callback<LoginEntity>() {
            @Override
            public void onResponse(Call<LoginEntity> call, Response<LoginEntity> response) {
                int code = response.code();
                LoginEntity loginEntity = response.body();
                if (code == 200) {
                    String status = loginEntity.getStatus();
                    if (loginEntity != null) {
                        if (status.equals("0000")) {
                            String headPic = loginEntity.getResult().getHeadPic();
                            String nickName = loginEntity.getResult().getNickName();
                            int userId = loginEntity.getResult().getUserId();
                            String sessionId = loginEntity.getResult().getSessionId();
                            //存值
                            SpUtil.put("headPic", headPic);
                            SpUtil.put("nickName", nickName);
                            SpUtil.put("userId", String.valueOf(userId));
                            SpUtil.put("sessionId", sessionId);
                            httpListener.zhuLogin(loginEntity);
                            Log.i("TAG",loginEntity.getMessage());
                        } else {
                            httpListener.zhuHttpFaiure("失败了");
                        }

                    } else {
                        httpListener.zhuHttpFaiure("失败");
                    }
                } else {
                    httpListener.zhuHttpFaiure("网络错误" + code);
                }
            }

            @Override
            public void onFailure(Call<LoginEntity> call, Throwable t) {
                ToastUtil.ToastUtil("失败");
            }
        });


    }
}
