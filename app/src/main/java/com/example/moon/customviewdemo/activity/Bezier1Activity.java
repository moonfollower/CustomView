package com.example.moon.customviewdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.moon.customviewdemo.R;
import com.example.moon.customviewdemo.radarview.Bezier;

public class Bezier1Activity extends AppCompatActivity {

    private Bezier bezier1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier1);
        bezier1 = (Bezier) findViewById(R.id.bezier1);
    }
}
