package com.qianwang.study2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private MyFlowLayout mMyFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mMyFlowLayout = (MyFlowLayout) findViewById(R.id.flowlayout);

        for(int i=0;i<20;i++){

            TextView textView = new TextView(getApplicationContext());
            textView.setText("nihao");
            textView.setTextColor(Color.RED);
            mMyFlowLayout.addView(textView);
        }


    }
}
