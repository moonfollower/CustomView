package com.example.moon.customviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by moon on 2017/2/6.
 */

public class Bezier extends View {


    private Paint mPaint;
    private int centerX, centerY;

    private PointF start, end, control;

    public Bezier(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        start = new PointF(0,0);
        end = new PointF(0,0);
        control = new PointF(0,0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w/2;
        centerY = h/2;

        // 初始化数据点和控制点的位置
        start.x = centerX-200;
        start.y = centerY;
        end.x = centerX+200;
        end.y = centerY;
        control.x = centerX;
        control.y = centerY-100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 根据触摸位置更新控制点，并提示重绘
        control.x = event.getX();
        control.y = event.getY();
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x,start.y,mPaint);
        canvas.drawPoint(end.x,end.y,mPaint);
        canvas.drawPoint(control.x,control.y,mPaint);

        // 绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x,start.y,control.x,control.y,mPaint);
        canvas.drawLine(end.x,end.y,control.x,control.y,mPaint);

        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();

        path.moveTo(start.x,start.y);
        path.quadTo(control.x,control.y,end.x,end.y);

        canvas.drawPath(path, mPaint);
    }

    /*//画笔
    private Paint mPaint;
    //中心点
    private int centerX,centerY;
    //开始点、结束点、控制点1，控制点2
    private PointF start,end ,control;


    public Bezier(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        start = new PointF(0,0);
        end = new PointF(0,0);
        control = new PointF(0,0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        centerX = w/2;
        centerY = h/2;
        // 初始化数据点和控制点的位置
        start.x = centerX-200;
        start.y = centerY;
        end.x = centerX+200;
        end.y = centerY;
        control.x = centerX;
        control.y = centerY-100;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        control.x = event.getX();
        control.y = event.getY();
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制数据点和控制点
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x,start.y,mPaint);
        canvas.drawPoint(end.x,end.y,mPaint);
        canvas.drawPoint(control.x,control.y,mPaint);


        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.DKGRAY);
        canvas.drawLine(start.x,start.y,control.x,control.y,mPaint);
        canvas.drawLine(end.x,end.y,control.x,control.y,mPaint);

        //绘制贝塞尔曲线
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(6);
        Path path = new Path();
        path.moveTo(start.x,start.y);
        path.quadTo(control.x,control.y,end.x,end.y);

        canvas.drawPath(path,mPaint);


    }*/
}