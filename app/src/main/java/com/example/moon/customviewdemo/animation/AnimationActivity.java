package com.example.moon.customviewdemo.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.moon.customviewdemo.R;

public class AnimationActivity extends AppCompatActivity {

    private TextView textView;
    private Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        init();
    }


    private void init(){
        textView = (TextView) findViewById(R.id.tv_scale);
        anim = AnimationUtils.loadAnimation(this,R.anim.scaleanim);
        textView.setAnimation(anim);
    }
}
