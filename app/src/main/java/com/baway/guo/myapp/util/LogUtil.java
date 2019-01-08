package com.baway.guo.myapp.util;

import android.text.TextUtils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
public class LogUtil {
    public static void init() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(2)
                .methodOffset(3)
                .logStrategy(null)
                .tag("JingQ")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    public static void d(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Logger.d(msg);
        }
    }

    public static void d(String tag, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Logger.t(tag).d(msg);
        }
    }

    public static void e(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Logger.e(msg);
        }
    }

    public static void i(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Logger.i(msg);
        }
    }

}
