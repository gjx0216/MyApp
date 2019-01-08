package com.baway.guo.myapp.net;

import com.baway.guo.myapp.util.SpUtil;
import com.baway.guo.myapp.util.UserContant;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OkHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        //得到原来的请求
        Request request = chain.request();
        //设置Build   方便得到
        Request.Builder builder = request.newBuilder();
        //获取userid和sessionId
        builder.addHeader("userId", SpUtil.getString(UserContant.USERID, ""));
        builder.addHeader("sessionId", SpUtil.getString(UserContant.SESSIONID, ""));

        //添加请求头
        builder.addHeader("userId", "userId");
        builder.addHeader("sessionId", "sessionId");
        //得到request
        request = builder.build();
        return chain.proceed(request);
    }
}
