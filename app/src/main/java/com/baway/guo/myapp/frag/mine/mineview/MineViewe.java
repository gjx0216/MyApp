package com.baway.guo.myapp.frag.mine.mineview;

public interface MineViewe<T> {

    void httpSuccess(T data);

    void htSuccess(String name);

    void htSuccess1(String name);

    void httpFaiuere(String msg);
}
