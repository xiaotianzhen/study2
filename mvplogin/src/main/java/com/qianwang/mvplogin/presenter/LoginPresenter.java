package com.qianwang.mvplogin.presenter;

import com.qianwang.mvplogin.presenter.Ipresenter.ILoginPresenter;
import com.qianwang.mvplogin.view.IView.ILoginView;

/**
 * Created by sky on 2017/4/19.
 */

public class LoginPresenter extends ILoginPresenter {
    private ILoginView mLoginView;

    public LoginPresenter(ILoginView loginView) {
        mLoginView = loginView;
    }

    @Override
    public boolean checkPhoneLogin(String phone, String verifyCode) {
        return false;
    }

    @Override
    public boolean checkUserNameLogin(String userName, String password) {
        return false;
    }

    @Override
    public void phoneLogin(String phone, String verifyCode) {

    }

    @Override
    public void userNameLogin(String userName, String password) {

    }

    @Override
    public void sendVerifyCode(String phoneNum) {

    }

    @Override
    public void start() {

    }

    @Override
    public void finish() {

    }
}
