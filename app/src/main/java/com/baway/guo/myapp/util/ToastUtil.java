package com.baway.guo.myapp.util;

import android.text.TextUtils;
import android.widget.Toast;
import com.baway.guo.myapp.app.App;

public class ToastUtil {

    public static void ToastUtil(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Toast.makeText(App.context, "", Toast.LENGTH_LONG).show();
    }
}
