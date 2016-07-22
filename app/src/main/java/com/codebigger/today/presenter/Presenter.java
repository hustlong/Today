package com.codebigger.today.presenter;

/**
 * Created by code on 16/7/22.
 */
public interface Presenter<V> {

    void attachView(V view);

    void detachView();
}
