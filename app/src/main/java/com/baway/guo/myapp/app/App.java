package com.baway.guo.myapp.app;

import android.app.Application;
import android.content.Context;

import com.baway.guo.myapp.net.OkHttpUtil;
import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        OkHttpUtil.init();
        Fresco.initialize(this);
    }
}
