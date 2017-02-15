package com.example.moon.customviewdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moon.customviewdemo.PieData;
import com.example.moon.customviewdemo.PieView;
import com.example.moon.customviewdemo.R;
import com.example.moon.customviewdemo.animation.AnimationActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private PieView pieView = null;
    private List<PieData> list = new ArrayList<>();

    private Button radar,bezier1,bezier2,anim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        init();
    }

    //初始化
    private void init(){
        pieView = (PieView) findViewById(R.id.pieView_activity_main);
        pieView.setData(list);
        radar = (Button) findViewById(R.id.btn_radard);
        bezier1 = (Button) findViewById(R.id.btn_bezier1);
        bezier2 = (Button) findViewById(R.id.btn_bezier2);
        anim = (Button) findViewById(R.id.btn_anim);


        radar.setOnClickListener(this);
        bezier1.setOnClickListener(this);
        bezier2.setOnClickListener(this);
        anim.setOnClickListener(this);
    }

    //模拟数据加载
    private void initData(){
        for (int i = 0; i < 5; i++) {
            PieData d = new PieData("数据"+i,2*i);
            list.add(d);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.btn_radard:
                intent = new Intent(MainActivity.this,RadarActivity.class);
                break;
            case R.id.btn_bezier1:
                intent = new Intent(MainActivity.this,Bezier1Activity.class);
                break;
            case R.id.btn_bezier2:
                break;
            case R.id.btn_anim:
                intent  = new Intent(MainActivity.this, AnimationActivity.class);
                break;
        }
        if(null != intent){
            startActivity(intent);
        }

    }
}
