package com.qianwang.mvplogin.presenter.Ipresenter;

import android.view.View;

/**
 * Created by sky on 2017/4/21.
 */

public abstract  class IMainPresenter implements BasePresenter {
    @Override
    public void start() {


    }
    @Override
    public void finish() {


    }
    //初始化 FragmentTabHost
    protected   abstract void initFragment();
    //展示tab选项卡
     protected  abstract View getTabItemView(int i);
}
