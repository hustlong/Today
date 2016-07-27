package com.codebigger.today.presenter;

import android.os.Handler;

import com.codebigger.today.model.MainModel;
import com.codebigger.today.model.ResultModelBean;
import com.codebigger.today.view.MainView;

/**
 * Created by code on 16/7/22.
 * 联通model 和 view ,从 model 获取数据 传给 view
 */
public class MainPresenter implements Presenter<MainView>,IMainPresenter {

    private MainView mMainView;

    private MainModel mMainModel;

    private Handler mHandler;

    public MainPresenter(String day,MainView view) {
        attachView(view);
        mMainModel = new MainModel(day,this);
        mHandler = new Handler();
    }

    public void loadData() {
        mMainModel.loadData();
    }

    public void loadNextData() {
        mMainModel.loadNextData();
    }

    public void loadLastData() {
        mMainModel.loadLastData();
    }

    @Override
    public void loadDataSuccess(final ResultModelBean resultModelBean) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mMainView.displayData(resultModelBean);
            }
        });

    }

    @Override
    public void loadDataFailure() {
        mMainView.showNothing();
    }

    @Override
    public void attachView(MainView view) {
        this.mMainView = view;
    }

    @Override
    public void detachView() {
        this.mMainView = null;
    }
}
