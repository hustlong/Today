package com.codebigger.today.model;

import java.util.List;

/**
 * Created by code on 16/7/22.
 */
public class RootModelBean {

    private String msg;

    private String retCode;

    private List<ResultModelBean> result ;

    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setRetCode(String retCode){
        this.retCode = retCode;
    }
    public String getRetCode(){
        return this.retCode;
    }
    public void setResult(List<ResultModelBean> result){
        this.result = result;
    }
    public List<ResultModelBean> getResult(){
        return this.result;
    }

}
