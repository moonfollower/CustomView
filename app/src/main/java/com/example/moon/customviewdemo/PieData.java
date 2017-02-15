package com.example.moon.customviewdemo;

import android.support.annotation.NonNull;

/**
 * 饼状图数据
 */

public class PieData {
    //用户关心数据
    private String name;//名字
    private int value;//值
    private float percentage;//百分比

    //非用户关心属性

    private int color; //颜色
    private float angle; //角度


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public PieData(@NonNull String name, @NonNull int value){
        this.name = name;
        this.value = value;
    }

}
