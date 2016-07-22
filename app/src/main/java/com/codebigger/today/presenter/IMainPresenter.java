package com.codebigger.today.presenter;

import com.codebigger.today.model.ResultModelBean;

/**
 * Created by code on 16/7/22.
 * 该接口负责与model通信.
 */
public interface IMainPresenter {

    void loadDataSuccess(ResultModelBean resultModelBean);

    void loadDataFailure();
}
