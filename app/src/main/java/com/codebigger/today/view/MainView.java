package com.codebigger.today.view;

import com.codebigger.today.model.ResultModelBean;
import com.codebigger.today.model.RootModelBean;

/**
 * Created by code on 16/7/22.
 * 处理一些业务的方法
 */
public interface MainView {

    void displayData(ResultModelBean resultModelBean);

    void showNothing();

}
