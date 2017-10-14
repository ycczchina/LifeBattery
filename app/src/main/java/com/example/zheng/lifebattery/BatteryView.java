package com.example.zheng.lifebattery;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Zheng on 10/13/17.
 */

public class BatteryView extends View {

    private Paint mBatteryPait;
    private Paint mPowerPaint;
    private float mBatteryStroke = 2.0f;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int barColor;

    int progress = 0;
    /**
     * 屏幕的高宽
     *
     * @param context
     */
    private int measureWidth;
    private int measureHeight;

    /**
     * 电池参数
     *
     * @param context
     */
    private float mBatteryHeight = 30.0f;// 电池高度
    private float mBatteryWidth = 60.0f;// 电池的宽度
    private float mCapHeight = 15.0f;
    private float mCapWidth = 5.0f;
    private float mRadius = 2.0f;

    /**
     * 电池电量
     *
     * @param context
     */
    private float mPowerPadding = 5.0f;
    private float mPowerHeight = mBatteryHeight - 2 * mBatteryStroke
            - mPowerPadding;
    private float mPowerWidth = mBatteryWidth - 2 * mBatteryStroke - mPowerPadding
            * 2;// 电池身体的总宽度
    private float mPower = 0f;

    /**
     * 矩形
     */
    private RectF mBatteryRectF;
    private RectF mCapRectF;
    private RectF mPowerRectF;

    public BatteryView(Context context) {
        super(context);
        initView();
    }

    public BatteryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BatteryView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        measureWidth = MeasureSpec.getSize(widthMeasureSpec);

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 设置电池画笔
         */
        mBatteryPait = new Paint();
        mBatteryPait.setColor(Color.GRAY);
        mBatteryPait.setStrokeWidth(mBatteryStroke);
        mBatteryPait.setStyle(Paint.Style.STROKE);
        mBatteryPait.setAntiAlias(true);
        /**
         * 电量画笔
         */
        mPowerPaint = new Paint();
        if(progress > 30) barColor = Color.rgb(91, 194, 54);
        else if(progress> 10) barColor = Color.rgb(255,165,0);
        else barColor = Color.RED;
        mPowerPaint.setColor(barColor);
        mPowerPaint.setStyle(Paint.Style.FILL);
        mPowerPaint.setStrokeWidth(mBatteryStroke);
        mPowerPaint.setAntiAlias(true);
        /**
         * 设置电池矩形
         */
        mBatteryRectF = new RectF(0, 0, mBatteryWidth,
                mBatteryHeight);

        /**
         * 设置电池盖矩形
         */

        mCapRectF = new RectF(mBatteryWidth, mBatteryHeight / 2 - mCapHeight / 2,
                mCapWidth + mBatteryWidth, mBatteryHeight / 2 + mCapHeight / 2);

        /**
         * 设置电量的矩形
         */

        mPowerRectF = new RectF(mBatteryWidth - mPowerPadding
                - mPowerWidth - mBatteryStroke, mPowerPadding, (mBatteryWidth
                - mPowerPadding - mBatteryStroke) - mPowerWidth * (100 - progress) / 100, mBatteryHeight - mPowerPadding);



//        canvas.translate(measureWidth / 2, measureHeight / 2);
        canvas.scale(5,5);
        canvas.drawRoundRect(mBatteryRectF, mRadius, mRadius, mBatteryPait);
        canvas.drawRoundRect(mCapRectF, mRadius, mRadius, mBatteryPait);
        canvas.drawRect(mPowerRectF, mPowerPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(mBatteryHeight * 0.8f);
        String text = "" + progress + "%";
        canvas.drawText(text, mCapWidth + mBatteryWidth + mCapWidth, mBatteryHeight * 0.8f, mPaint);

    }

    /*设置进度条进度, 外部调用*/
    public void setProgress(int progress) {
        if (progress > 100) {
            this.progress = 100;
        } else if (progress < 0) {
            this.progress = 0;
        } else {
            this.progress = progress;
        }
        postInvalidate();
    }
}
