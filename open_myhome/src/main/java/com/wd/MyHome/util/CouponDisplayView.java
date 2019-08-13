package com.wd.MyHome.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/8/9 19:23
 * @Description：描述信息
 */
public class CouponDisplayView extends LinearLayout {

    private Paint mPaint;
    /**
     * 圆间距
     */
    private float gap = 400;
    /**
     * 半径
     */
    private float radius = 30;
    /**
     * 圆数量
     */
    private int circleNum;

    private float remain;


    public CouponDisplayView(Context context) {
        super(context);
    }

    public CouponDisplayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

 /*   @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain==0){
            remain = (int)(w-gap)%(2*radius+gap);
        }
        circleNum = (int) ((w-gap)/(2*radius+gap));
    }*/


    public CouponDisplayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // for (int i=0;i<1;i++){
           // float x = gap+radius+remain/2+((gap+radius*2)*i);
            canvas.drawCircle(0,gap,radius,mPaint);
            canvas.drawCircle(getWidth(),gap,radius,mPaint);
       // }
    }
}
