package com.qianwang.mvplogin2.presenter.IPresenter;

/**
 * Created by sky on 2017/4/19.
 */

public interface ILoginPresenter {

    void doLogin(String username,String password);
    void clear();
    void onDestory();
}
