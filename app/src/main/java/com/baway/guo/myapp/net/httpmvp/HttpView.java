package com.baway.guo.myapp.net.httpmvp;

public interface HttpView<T> {
    void httpSuccess(T data);

    void httpFaiuere(String msg);
}
