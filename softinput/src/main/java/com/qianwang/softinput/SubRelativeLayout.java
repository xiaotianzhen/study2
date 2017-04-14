package com.qianwang.softinput;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * Created by sky on 2017/4/13.
 */

public class SubRelativeLayout extends RelativeLayout {

    private OnSoftKeyboardListener mKeyboardListener;

    public SubRelativeLayout(Context context) {
        super(context);
    }

    public SubRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SubRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i("520it", "" + "************  onMeasure  **************");

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        Log.i("520it", "" + "************   onLayout  **************");
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i("520it", "" + "***********  onSizeChanged  ***************");
        mKeyboardListener.onSoftKeyboardChage(w,h,oldw,oldh);
        super.onSizeChanged(w, h, oldw, oldh);

    }

    public void setKeyboardListener(OnSoftKeyboardListener keyboardListener) {
        mKeyboardListener = keyboardListener;
    }

    public interface OnSoftKeyboardListener {

        public void onSoftKeyboardChage(int w, int h, int oldw, int oldh);
    }
}
