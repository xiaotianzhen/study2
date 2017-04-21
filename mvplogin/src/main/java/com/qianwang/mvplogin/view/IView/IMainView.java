package com.qianwang.mvplogin.view.IView;

import android.content.Context;

/**
 * Created by sky on 2017/4/21.
 */

public interface IMainView extends BaseView{
    @Override
    void showLoading();

    @Override
    void dismissLoading();

    @Override
    void showMsg(String msg);

    @Override
    void showMsg(int msgId);

    @Override
    Context getContext();
}
