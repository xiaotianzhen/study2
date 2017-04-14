package com.qianwang.scrollclash.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by sky on 2017/4/12.
 */

public class MyAdapter extends FragmentPagerAdapter {

  private List<Fragment> mList=new ArrayList<Fragment>();
    public MyAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        mList=list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {

        Log.i("520it", "size" + "**************************"+mList.size());
        return mList.size();
    }
}
