package com.codebigger.today.model;

/**
 * Created by code on 16/7/22.
 */
public class ResultModelBean {

    private String date;

    private int day;

    private String event;

    private String id;

    private int month;

    private String title;

    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
    public void setDay(int day){
        this.day = day;
    }
    public int getDay(){
        return this.day;
    }
    public void setEvent(String event){
        this.event = event;
    }
    public String getEvent(){
        return this.event;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setMonth(int month){
        this.month = month;
    }
    public int getMonth(){
        return this.month;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }

}
