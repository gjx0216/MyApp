package com.baway.guo.myapp.login.loginview;

public interface LoginView<T> {
    void onSuccess(T result);

    void onFailure(String msg);
}
