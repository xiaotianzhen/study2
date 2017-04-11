package com.qianwang.collapseview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Collapseview mCollapseview1;
    private Collapseview mCollapseview2;
    private Collapseview mCollapseview3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCollapseview1= (Collapseview) findViewById(R.id.coll1);
        mCollapseview1.setContent(R.layout.content_img);
        mCollapseview1.setNum(1+"");
        mCollapseview1.setTitle("早上");

        mCollapseview2= (Collapseview) findViewById(R.id.coll2);
        mCollapseview2.setContent(R.layout.content_img2);
        mCollapseview2.setNum(2+"");
        mCollapseview2.setTitle("中午");

        mCollapseview3= (Collapseview) findViewById(R.id.coll3);
        mCollapseview3.setContent(R.layout.content_img3);
        mCollapseview3.setNum(3+"");
        mCollapseview3.setTitle("下午");



    }
}
