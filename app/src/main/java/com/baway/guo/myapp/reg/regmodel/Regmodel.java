package com.baway.guo.myapp.reg.regmodel;

import com.baway.guo.myapp.net.OkHttpUtil;
import com.baway.guo.myapp.net.httpmvp.HttpListener;
import com.baway.guo.myapp.reg.regentity.RegEntity;
import com.baway.guo.myapp.reg.regretrofitapi.Regretrofitapi;
import com.baway.guo.myapp.util.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Regmodel {
    //注册
    public void postMethod0(String phone, String pwd, String url, final HttpListener httpListener) {
        Regretrofitapi regretrofitapi = OkHttpUtil.retrofit.create(Regretrofitapi.class);
        Call<RegEntity> objectCall = regretrofitapi.postMethod0(phone, pwd, url);
        objectCall.enqueue(new Callback<RegEntity>() {
            @Override
            public void onResponse(Call<RegEntity> call, Response<RegEntity> response) {
                int code = response.code();
                if (code == 200) {
                    RegEntity regEntity = response.body();
                    String status = regEntity.getStatus();
                    if (regEntity != null) {
                        if (status.equals("0000")) {
                            httpListener.zhuLogin(regEntity);
                        } else {
                            httpListener.zhuHttpFaiure(regEntity.getMessage());
                        }
                    } else {
                        httpListener.zhuHttpFaiure("空");
                    }
                } else {
                    httpListener.zhuHttpFaiure("服务器错误");
                }

            }

            @Override
            public void onFailure(Call<RegEntity> call, Throwable t) {
                ToastUtil.ToastUtil("失败");
            }
        });


    }
}
