package com.baway.guo.myapp.net;

import com.baway.guo.myapp.util.LogUtil;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OkLogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        String method = request.method();
        HttpUrl url = request.url();
        LogUtil.d("本次请求", ",url" + url.toString() + ",method" + method);

        Headers headers = request.headers();
        Set<String> names = headers.names();
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            String s = headers.get(next);
            LogUtil.d(next + ":" + s);
        }
        return chain.proceed(request);
    }
}
