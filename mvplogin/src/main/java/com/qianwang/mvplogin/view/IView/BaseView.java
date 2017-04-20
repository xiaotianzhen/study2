package com.qianwang.mvplogin.view.IView;

import android.content.Context;

/**
 * Created by sky on 2017/4/19.
 */

public interface BaseView {

    /**
     * 数据加载或耗时加载时界面显示
     */
    void showLoading();

    /**
     * 数据加载或耗时加载完成时界面显示
     */
    void dismissLoading();

    /**
     * 消息提示，如 Toast，Dialog等
     */
    void showMsg(String msg);
    void showMsg(int msgId);

    /**
     * 获取Context
     * @return
     */
    Context getContext();
}
