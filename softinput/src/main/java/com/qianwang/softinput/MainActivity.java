package com.qianwang.softinput;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SubRelativeLayout subrel_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int screenHeight=getResources().getDisplayMetrics().heightPixels;
        final int keyBoardHeight=screenHeight/3;
        subrel_layout= (SubRelativeLayout) findViewById(R.id.subrel_layout);

        subrel_layout.setKeyboardListener(new SubRelativeLayout.OnSoftKeyboardListener() {


            public void onSoftKeyboardChage(int w, int h, int oldw, int oldh) {
                if (oldh-h>keyBoardHeight){
                    System.out.println("----> 软键盘弹起");
                }else if(h-oldh>keyBoardHeight){
                    System.out.println("----> 软键盘收起");
                }
            }
        });
    }
}
