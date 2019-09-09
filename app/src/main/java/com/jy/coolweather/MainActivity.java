package com.jy.coolweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.jy.coolweather.utils.SpUtil;
import com.kkk.mvp.base.BaseActivity;

import java.util.ArrayDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String weatherString = (String) SpUtil.getParam("weather", "null");

        if(!weatherString.equals("null")){
            Intent intent = new Intent(this,WeatherActivity.class);
            startActivity(intent);
            finish();
        }else{
            addFragment(getSupportFragmentManager(),ChooseAreaFragment.class,R.id.frame,null);
        }


    }
}
