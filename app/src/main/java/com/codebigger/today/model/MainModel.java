package com.codebigger.today.model;

import android.util.Log;

import com.codebigger.today.presenter.IMainPresenter;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by code on 16/7/22.
 * 业务具体逻辑,包括请求,解析,检索,保存等.
 */
public class MainModel {

    /** 请求地址 */
    private static String  baseUrl = "http://apicloud.mob.com/appstore/history/query?key=10193c61f77c8&day=";

    /** 请求参数 */
    private String day = "0101";

    /** presenter */
    private IMainPresenter mIMainPresenter;

    /** Data */
    private RootModelBean rootModelBean;
    private List<ResultModelBean> resultList;

    /**  当前展示的第几条信息 */
    private int curIndex = 0;

    /** 是不是正在下载数据 */
    private boolean isDownloading = false;

    public MainModel(String day, IMainPresenter mIMainPresenter) {
        this.day = day;
        this.mIMainPresenter = mIMainPresenter;

    }

    public void loadData() {
        isDownloading = true;
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(baseUrl + day, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                mIMainPresenter.loadDataFailure();
                isDownloading = false;
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i("loadData",responseString);
                try {
                    Gson gson = new Gson();
                    rootModelBean = gson.fromJson(responseString,RootModelBean.class);
                    resultList = rootModelBean.getResult();

                    /** 默认展示第一条信息 */
                    mIMainPresenter.loadDataSuccess(resultList.get(curIndex));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                isDownloading = false;
            }
        });
    }

    public void loadNextData() {
        if (resultList == null) {
            mIMainPresenter.loadDataFailure();
            if (!isDownloading)
                loadData();
            return;
        }
        curIndex++;
        if (curIndex < resultList.size())
            mIMainPresenter.loadDataSuccess(resultList.get(curIndex));
        else {
            curIndex = resultList.size();
            mIMainPresenter.loadDataFailure();
        }

    }

    public void loadLastData() {
        if (resultList == null) {
            mIMainPresenter.loadDataFailure();
            if (!isDownloading)
                loadData();
            return;
        }
        curIndex--;
        if (curIndex > -1) {
            mIMainPresenter.loadDataSuccess(resultList.get(curIndex));
        }
        else {
            curIndex = -1;
            mIMainPresenter.loadDataFailure();
        }

    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


}
