package com.codebigger.today.ui;

import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codebigger.today.R;
import com.codebigger.today.model.ResultModelBean;
import com.codebigger.today.presenter.MainPresenter;
import com.codebigger.today.view.MainView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,MainView{

    private TextView titleTv,dateTv,contentTv;
    private Button lastBtn,nextBtn;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
    }

    private void initview() {
        titleTv = (TextView) findViewById(R.id.titleTV);
        dateTv = (TextView) findViewById(R.id.dateTv);
        contentTv = (TextView) findViewById(R.id.contentTv);
        lastBtn = (Button) findViewById(R.id.lastBtn);
        nextBtn = (Button) findViewById(R.id.nextBtn);

        lastBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

        SimpleDateFormat format = new SimpleDateFormat("MMdd");
        Log.i("mainactivity", "format date = " + format.format(new Date()));

        getSupportActionBar().setTitle(getSupportActionBar().getTitle() + " " +format.format(new Date()));

        mainPresenter = new MainPresenter(format.format(new Date()),this);
        mainPresenter.loadData();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lastBtn:
                mainPresenter.loadLastData();
                break;
            case R.id.nextBtn:
                mainPresenter.loadNextData();
        }
    }

    @Override
    protected void onDestroy() {
        mainPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void displayData(ResultModelBean resultModelBean) {
        titleTv.setText(resultModelBean.getTitle());
        dateTv.setText(turn2Date(resultModelBean.getDate()));
        contentTv.setText(resultModelBean.getEvent());
    }

    @Override
    public void showNothing() {
        titleTv.setText("空空如也");
        dateTv.setText("空空如也");
        contentTv.setText("空空如也~~~");
    }

    private String turn2Date(String date) {
        int year_index = date.length() - 4;
        return "公元 " + date.substring(0,year_index)+" 年 "
                + date.substring(year_index,year_index + 2) + " 月 "
                + date.substring(year_index + 2,year_index + 4) + " 日 ";
    }
}
