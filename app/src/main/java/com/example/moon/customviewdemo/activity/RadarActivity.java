package com.example.moon.customviewdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.moon.customviewdemo.R;
import com.example.moon.customviewdemo.radarview.RadarView;

public class RadarActivity extends AppCompatActivity {

    private RadarView radarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);
        radarView = (RadarView) findViewById(R.id.radar_view);
    }
}
