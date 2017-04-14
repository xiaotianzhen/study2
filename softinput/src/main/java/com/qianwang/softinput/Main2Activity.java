package com.qianwang.softinput;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    private SubRelativeLayout2 mRelativeLayout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        mRelativeLayout2= (SubRelativeLayout2) findViewById(R.id.subrel_layout2);

        mRelativeLayout2.setKeyboardListener(new SubRelativeLayout2.OnSoftKeyboardListener() {
            @Override
            public void onSoftKeyboardChage() {

                boolean result=isSoftKeyboardShow(mRelativeLayout2);
                if(result){
                    Log.i("520it", "" + "************ 展示 **************");
                }else {
                    Log.i("520it", "" + "************  收起 **************");
                }
            }
        });

    }

    public boolean isSoftKeyboardShow(View view){

        int screenHeight=getResources().getDisplayMetrics().heightPixels;
        int  compareHeight=screenHeight/3;   //一个测量的标准，当键盘的高度大于屏幕高度的1/3的时候，认为键盘展示，否则相反
        Rect rect=new Rect();
        view.getWindowVisibleDisplayFrame(rect);   //测量view的可见宽度，就是不包括键盘覆盖的区域
        int viewVisiblity=rect.bottom;
        int viewHeight=view.getBottom();           //布局的高度
        int diffHeight=viewHeight-viewVisiblity;   //键盘展示的高度

        return diffHeight>=compareHeight;
    }
}
