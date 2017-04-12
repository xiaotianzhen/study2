package com.qianwang.study2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sky on 2017/4/11.
 */

public class RectTextView extends TextView {
    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private String mText;
    private float textSie;

    public RectTextView(Context context) {
        this(context,null);
        initPint();
    }

    public RectTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        initPint();
    }

    public RectTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeHeigth = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (modeWidth == MeasureSpec.EXACTLY) {

            if (modeHeigth != MeasureSpec.EXACTLY) {

                mWidth = sizeWidth;
                mHeight = mWidth;

            } else {
                mWidth = sizeWidth;
                mHeight = sizeHeight;
            }

        }

        if (modeWidth != MeasureSpec.EXACTLY) {

            if (modeHeigth == MeasureSpec.EXACTLY) {
                mHeight = sizeHeight;
                mHeight = mWidth;
            } else {

                mPaint.setTextSize(dp2px(getContext(),getTextSize()+1));  //gettextSize 是float,变为宽度的时候可能会变小，所以+1；
                Rect rect=new Rect();
                mPaint.getTextBounds(mText,0,mText.length(),rect);

                mWidth = rect.width()/2 + getPaddingLeft() + getPaddingRight();
                mHeight=rect.height()+getPaddingTop()+getPaddingBottom();
            }
        }

        setMeasuredDimension(mWidth, mHeight);


    }
    private void initPint(){

        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.GREEN);

    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        this.mText=text.toString();
    }

    @Override
    public void setTextSize(float size) {
        super.setTextSize(size);
        this.textSie=size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF=new RectF(0,0,mWidth,mHeight);
        canvas.drawRoundRect(rectF,20,20,mPaint);
    }

    /**
     * 将px,转换为dp
     * @param context
     * @param dpVal
     * @return
     */
    private int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

}
