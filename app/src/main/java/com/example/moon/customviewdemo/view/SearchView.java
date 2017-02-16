package com.example.moon.customviewdemo.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by moon on 2017/2/16.
 */

public class SearchView extends View {

    //画笔
    private Paint mPaint;
    //宽高
    private int mWidth,mHeight;

    // 这个视图拥有的状态
    public static enum State {
        NONE,
        STARTING,
        SEARCHING,
        ENDING
    }

    // 当前的状态(非常重要)
    private State mCurrentState = State.NONE;

    // 放大镜与外部圆环
    private Path path_srarch;
    private Path path_circle;

    // 测量Path 并截取部分的工具
    private PathMeasure mMeasure;

    // 默认的动效周期 2s
    private int defaultDuration = 2000;

    // 控制各个过程的动画
    private ValueAnimator mStartingAnimator;
    private ValueAnimator mSearchingAnimator;
    private ValueAnimator mEndingAnimator;

    // 动画数值(用于控制动画状态,因为同一时间内只允许有一种状态出现,具体数值处理取决于当前状态)
    private float mAnimatorValue = 0;

    // 动效过程监听器
    private ValueAnimator.AnimatorUpdateListener mUpdateListener;
    private Animator.AnimatorListener mAnimatorListener;

    // 用于控制动画状态转换
    private Handler mAnimatorHandler;

    // 判断是否已经搜索结束
    private boolean isOver = false;

    private int count = 0;//次数

    //判断控件是否在至行中
    private boolean isOperation = false;

    public SearchView(Context context) {
        this(context,null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAll();
    }

    //初始化
    private void initAll(){
        initPaint();
        initPath();
        initListener();
        initHandler();
        initAnimator();
        // 进入开始动画
        /*mCurrentState = State.STARTING;
        mStartingAnimator.start();*/
    }


    //开始搜索。
    public void beginSearch(){
        // 进入开始动画
        isOperation = true;
        isOver = false;

        mCurrentState = State.STARTING;
        mStartingAnimator.start();

    }

    //搜索结束
    public void stopSearch(){
        isOver = true;
        isOperation = false;
    }

    //判断控件是否在执行
    public boolean isOperation(){
        return this.isOperation;
    }


    //初始化画笔
    private void initPaint(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(15);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);//?
        mPaint.setAntiAlias(true);
    }
    //初始化路径
    private void initPath(){
        path_srarch = new Path();
        path_circle = new Path();

        mMeasure = new PathMeasure();
        //搜索圆环
        RectF oval1 = new RectF(-50,-50,50,50);
        path_srarch.addArc(oval1,45f,359.9f);//// 注意,不要到360度,否则内部会自动优化,测量不能取到需要的数值

        //外部圆环
        RectF oval2 = new RectF(-100,-100,100,100);
        path_circle.addArc(oval2,45f,359.9f);

        //搜索把手
        float[] pos = new float[2];
        mMeasure.setPath(path_circle, false);  // 放大镜把手的位置

        mMeasure.getPosTan(0, pos, null);//用于得到路径上某一长度的位置以及该位置的正切值(存放在pos里)

        path_srarch.lineTo(pos[0], pos[1]); // 放大镜把手

    }

    //动画监听器
    private void initListener(){
        mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue =(float)valueAnimator.getAnimatedValue();
                invalidate();
            }
        };

        mAnimatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                // getHandle发消息通知动画状态更新
                mAnimatorHandler.sendEmptyMessage(0);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        };




    }

    //初始化handler
    private void initHandler(){
        mAnimatorHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (mCurrentState){
                    case STARTING:
                        // 从开始动画转换到搜索动画
                        isOver = false;
                        mCurrentState = State.SEARCHING;
                       // mStartingAnimator.removeAllListeners();
                        mSearchingAnimator.start();

                        break;
                    case SEARCHING:

                        if (!isOver) {  // 如果搜索未结束 则继续执行搜索动画
                            mSearchingAnimator.start();

                          /*  Log.e("Update", "RESTART");
                            count++;
                            if (count>2){       // count大于2则进入结束状态
                                isOver = true;
                            }*/

                        } else {        // 如果搜索已经结束 则进入结束动画
                            mCurrentState = State.ENDING;
                            mEndingAnimator.start();
                        }
                        break;
                    case ENDING:
                        mCurrentState = State.NONE;
                        break;

                }

            }
        };

    }

    //动画初始化
    private void initAnimator() {
        mStartingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);
        mSearchingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);
        mEndingAnimator = ValueAnimator.ofFloat(1, 0).setDuration(defaultDuration);

        mStartingAnimator.addUpdateListener(mUpdateListener);
        mSearchingAnimator.addUpdateListener(mUpdateListener);
        mEndingAnimator.addUpdateListener(mUpdateListener);

        mStartingAnimator.addListener(mAnimatorListener);
        mSearchingAnimator.addListener(mAnimatorListener);
        mEndingAnimator.addListener(mAnimatorListener);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSearch(canvas);

    }

    //绘制视图
    private void drawSearch(Canvas canvas){
        mPaint.setColor(Color.WHITE);
        canvas.translate(mWidth/2,mHeight/2);
        canvas.drawColor(Color.parseColor("#0082D7"));

        switch (mCurrentState){
            case NONE:
                canvas.drawPath(path_srarch,mPaint);
                break;
            case STARTING:
                mMeasure.setPath(path_srarch,false);
                Path dst1 = new Path();
                mMeasure.getSegment(mMeasure.getLength()*mAnimatorValue,mMeasure.getLength(),dst1,true);
                canvas.drawPath(dst1,mPaint);

                break;
            case SEARCHING:

                mMeasure.setPath(path_circle,false);
                Path dst2 = new Path();
                float stop = mMeasure.getLength()*mAnimatorValue;
                float start = (float) (stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * 200f));
                mMeasure.getSegment(start, stop, dst2, true);
                canvas.drawPath(dst2, mPaint);

                break;
            case ENDING:
                mMeasure.setPath(path_srarch, false);
                Path dst3 = new Path();
                mMeasure.getSegment(mMeasure.getLength() * mAnimatorValue, mMeasure.getLength(), dst3, true);
                canvas.drawPath(dst3, mPaint);
                break;

        }

    }
}
