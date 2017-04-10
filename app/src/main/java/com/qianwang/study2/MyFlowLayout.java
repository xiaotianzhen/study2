package com.qianwang.study2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by sky on 2017/4/10.
 */

public class MyFlowLayout extends ViewGroup {


    private int verticalSpacing = 20;

    public MyFlowLayout(Context context) {
        super(context);
    }

    public MyFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHigeht = MeasureSpec.getMode(heightMeasureSpec);


        int paddingLefr = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int useWidth = paddingLefr + paddingRight;
        int usehight = paddingTop + paddingBottom;

        int childMaxHeightInThisLine = 0;

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {

            View child = getChildAt(i);

            if (child.getVisibility() != GONE) {


                int childUseWidth = 0;
                int childUseHeight = 0;
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                childUseWidth += getMeasuredWidth();
                childUseHeight += getMeasuredHeight();

                LayoutParams childLayoutParams = child.getLayoutParams();
                MarginLayoutParams params = (MarginLayoutParams) childLayoutParams;
                int childLeftMargin = params.leftMargin;
                int childTopMargin = params.topMargin;
                int childRightMargin = params.rightMargin;
                int childBottomMargin = params.bottomMargin;

                childUseWidth += childLeftMargin + childRightMargin;
                childUseHeight += childTopMargin + childBottomMargin;

                if (useWidth + childUseWidth < widthMeasureSpec) {

                    useWidth += childUseWidth;
                    if (childUseHeight > usehight) {

                        childMaxHeightInThisLine = childUseHeight;
                    }

                } else {

                    usehight += childMaxHeightInThisLine + verticalSpacing;
                    useWidth = getPaddingLeft() + getPaddingRight() + useWidth;
                    childMaxHeightInThisLine = usehight;


                }

            }
        }

        usehight += childMaxHeightInThisLine;

        setMeasuredDimension(widthMeasureSpec, usehight);
    }

    @Override
    protected void onLayout(boolean c, int l, int t, int r, int b) {

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int childStartLayoutX = paddingLeft;
        int childStartLayoutY = paddingTop;

        int useWidth = paddingLeft + paddingRight;

        int childMaxHeight = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {

                int childNeedWidth, childNeedHeight;
                int left, top, right, bottom;

                int childMeasureWidth = getMeasuredWidth();
                int childMeasureHeight = getMeasuredHeight();

                LayoutParams childLayoutParams = child.getLayoutParams();
                MarginLayoutParams params = (MarginLayoutParams) childLayoutParams;
                int childLeftMargin = params.leftMargin;
                int childRightMargin = params.rightMargin;
                int childTopMargin = params.topMargin;
                int childBottomMargin = params.bottomMargin;

                childNeedWidth = childMeasureWidth + childLeftMargin + childRightMargin;
                childNeedHeight = childMeasureHeight + childBottomMargin + childTopMargin;

                if (childNeedWidth + useWidth < r - 1) {

                    if (childNeedHeight > childMaxHeight) {
                        childMaxHeight = childNeedHeight;
                    }

                    left = childStartLayoutX + childLeftMargin;
                    top = childStartLayoutY + childTopMargin;
                    right = left +childMeasureWidth;
                    bottom = top + childMeasureHeight;

                    useWidth += left;
                    childStartLayoutX += childNeedWidth;

                } else {

                    childStartLayoutY += childMaxHeight + verticalSpacing;

                    left = paddingLeft + childLeftMargin;
                    top =childStartLayoutY+childTopMargin;
                    right=left+childMeasureWidth;
                    bottom=top+childMeasureHeight;


                }


            }
        }
    }
}
