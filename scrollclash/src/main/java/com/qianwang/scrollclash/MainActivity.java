package com.qianwang.scrollclash;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import com.qianwang.scrollclash.adapter.MyAdapter;
import com.qianwang.scrollclash.adapter.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.vp);
        HomeFragment homeFragment = new HomeFragment();
        UserFragment userFragment=new UserFragment();

        mFragments.add(homeFragment);
        mFragments.add(userFragment);

        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), mFragments));

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                mViewPager.getParent().requestDisallowInterceptTouchEvent(true); //不允许mViewpager拦截ontouch  ，因为容器先获得ontouch，然后被拦截了
                return false;
            }
        });
    }
}


