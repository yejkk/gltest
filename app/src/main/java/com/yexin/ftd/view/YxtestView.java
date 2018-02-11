package com.yexin.ftd.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yexin.ftd.ui.Mime.LoginPage.LoginFragment;

import vod.clearcrane.com.myapplication.utils.LogUtil;

/**
 * Created by yexin on 2018/1/25.
 */

public class YxtestView extends View {
    private final String TAG =YxtestView.class.getName();

    public YxtestView(Context context) {
        super(context);
    }

    public YxtestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public YxtestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public YxtestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onDraw(Canvas canvas) {
        LogUtil.INSTANCE.i(TAG,"draw");
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(20);

        canvas.drawText("欢迎光临",-100,-100,paint);

        canvas.rotate(10);
        ObjectAnimator nObjectAnimator =  ObjectAnimator.ofFloat(this,"rotationX",0,270,0);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }





}
