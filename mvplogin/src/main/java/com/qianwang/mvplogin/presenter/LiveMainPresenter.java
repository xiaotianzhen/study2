package com.qianwang.mvplogin.presenter;



import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import com.astuetz.PagerSlidingTabStrip;
import com.qianwang.mvplogin.R;
import com.qianwang.mvplogin.adapter.ViewPagerAdapter;
import com.qianwang.mvplogin.view.LiveFragment;
import com.qianwang.mvplogin.view.MainActivity;

import java.util.ArrayList;


/**
 * Created by sky on 2017/4/21.
 */

public class LiveMainPresenter {

    private MainActivity mContent;
    private ImageView im_search;
    private ImageView im_message;
    private PagerSlidingTabStrip mTabStrip;
    private ViewPager viewpager;
    private ArrayList<String> mTitle;
    private ArrayList<android.support.v4.app.Fragment> mFragments;
    private ViewPagerAdapter mViewPagerAdapter;
    private LiveFragment mLiveFragment;


    public LiveMainPresenter(MainActivity context, LiveFragment liveFragment) {
        this.mContent = context;
        this.mLiveFragment=liveFragment;
    }

    public void initView(View view){
        im_search = (ImageView) view.findViewById(R.id.im_search);
        im_message = (ImageView) view.findViewById(R.id.im_message);
        mTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabStrip);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);

        initViewpage();
        mTabStrip.setViewPager(viewpager);
        mTabStrip.setTextColor(Color.WHITE);
        mTabStrip.setIndicatorColor(Color.WHITE);
        mTabStrip.setDividerColor(Color.GRAY);
        mTabStrip.setTextSize(view.getResources().getDimensionPixelSize(R.dimen.abc_text_size_menu_material));
        mTabStrip.setUnderlineHeight(1);
    }
    public void initViewpage(){

        mFragments = new ArrayList<Fragment>();
        mTitle = new ArrayList<String>();

        mTitle.add("最新");
        mTitle.add("最热");
        mTitle.add("达力");
        mTitle.add("活力");
        mTitle.add("英雄联盟");
        mTitle.add("王者荣耀");


        mViewPagerAdapter=new ViewPagerAdapter(mLiveFragment.getFragmentManager(),mTitle);
        viewpager.setAdapter(mViewPagerAdapter);
        viewpager.setCurrentItem(0);

    }

}
