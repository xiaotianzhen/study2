package com.qianwang.mvplogin.presenter.Ipresenter;

/**
 * Created by sky on 2017/4/19.
 */

public interface BasePresenter {

    /**
     * persenter 开始处理的方法
     */
    void start();

    /**
     * 处理一些销毁工作，在页面结束时调用
     */
    void finish();
}
