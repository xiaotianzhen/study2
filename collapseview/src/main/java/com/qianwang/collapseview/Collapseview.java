package com.qianwang.collapseview;


import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by sky on 2017/4/11.
 */

public class Collapseview extends LinearLayout {

    private RelativeLayout rell_titile;
    private RelativeLayout rell_content;
    private TextView tv_num;
    private TextView tv_titile;
    private ImageView im_arrow;
    private int parentWidthMeasureSpec;
    private int parentHeightMeasureSpec;
    private Context mContext;

    public Collapseview(Context context) {
        super(context);
    }

    public Collapseview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.collapseview, this);
        initView();
    }

    private void initView() {

        rell_titile = (RelativeLayout) findViewById(R.id.rell_titile);
        rell_content = (RelativeLayout) findViewById(R.id.rell_content);
        tv_num = (TextView) findViewById(R.id.tv_num);
        tv_titile = (TextView) findViewById(R.id.tv_title);
        im_arrow = (ImageView) findViewById(R.id.im_arrow);

        im_arrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                rotateArrow();
            }
        });

        collapse(rell_content);

    }

    public void setContent(int resID) {

        View view = LayoutInflater.from(mContext).inflate(resID, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);

        rell_content.addView(view);


    }

    /**
     * 旋转箭头
     */

    private void rotateArrow() {

        int degree = 0;
        if (im_arrow.getTag() == null || im_arrow.getTag().equals(true)) {

            Log.i("520it", "" + "************ expend **************");
            degree = -180;
            expend(rell_content);
            im_arrow.setTag(false);
        } else {
            Log.i("520it", "" + "**********  collapse ****************");
            degree = 0;
            collapse(rell_content);
            im_arrow.setTag(true);
        }

        im_arrow.animate().rotation(degree).setDuration(500);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentHeightMeasureSpec = heightMeasureSpec;
        parentWidthMeasureSpec = widthMeasureSpec;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    /**
     * 隐藏
     *
     * @param view
     */

    private void collapse(final View view) {


        final int measureHeight = view.getMeasuredHeight();

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = measureHeight - (int) (measureHeight * interpolatedTime);
                }
                requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(250);
        view.startAnimation(animation);


    }

    /**
     * 展开
     *
     * @param view
     */

    private void expend(final View view) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        view.measure(parentWidthMeasureSpec, parentHeightMeasureSpec);
        final int measuredWidth = view.getMeasuredWidth();
        final int measuredHeight = view.getMeasuredHeight();
        view.setVisibility(View.VISIBLE);

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view.getLayoutParams().height = measuredHeight;
                } else {
                    view.getLayoutParams().height = (int) (measuredHeight * interpolatedTime);
                }
                view.requestLayout();
            }


            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(250);
        view.startAnimation(animation);
    }


    public void setNum(String number) {

        if (!TextUtils.isEmpty(number)) {
            tv_num.setText(number);
        }
    }

    public void setTitle(String str) {

        if (!TextUtils.isEmpty(str)) {
            tv_titile.setText(str);
        }
    }

}
