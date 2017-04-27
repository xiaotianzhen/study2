package com.qianwang.study2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private MyFlowLayout mMyFlowLayout;
    private RectTextView  tab1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab1= (RectTextView) findViewById(R.id.tab1);

        tab1.setOnClickListener(this);


        mMyFlowLayout = (MyFlowLayout) findViewById(R.id.flowlayout);

        RectTextView textView = new RectTextView(getApplicationContext());
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(10, 5, 10, 5);
        textView.setTextSize(15);
        textView.setText("鞋子");
        textView.setTextColor(Color.BLUE);
        textView.setPaintColor(Color.RED);

        ViewGroup.LayoutParams params1=setViewMargin(textView);
        mMyFlowLayout.addView(textView,params1);

        RectTextView textView2 = new RectTextView(getApplicationContext());
        textView2.setGravity(Gravity.CENTER);
        textView2.setPadding(10, 5, 10, 5);
        textView2.setTextSize(15);
        textView2.setText("牛仔裤 ");
        textView2.setTextColor(Color.BLUE);
        textView2.setPaintColor(Color.RED);

        ViewGroup.LayoutParams params2=setViewMargin(textView2);
        mMyFlowLayout.addView(textView2,params2);


    }

    public static ViewGroup.LayoutParams setViewMargin(View view) {
        if (view == null) {
            return null;
        }
        ViewGroup.LayoutParams params = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginParams = null;
        //获取view的margin设置参数
        if (params instanceof ViewGroup.MarginLayoutParams) {

            marginParams = (ViewGroup.MarginLayoutParams) params;
        } else {
            //不存在时创建一个新的参数
            marginParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }


        //设置margin
        marginParams.setMargins(20, 20, 20, 20);
        view.setLayoutParams(marginParams);
        return marginParams;
    }

    @Override
    public void onClick(View view) {
        Log.i("520it", "" + "************  dianjile  **************");
        switch (view.getId()){
            case R.id.tab1:
                tab1.setPaintColor(Color.RED);
                break;
        }

    }
}
