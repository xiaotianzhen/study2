package com.qianwang.mvplogin2.view.IView;

/**
 * Created by sky on 2017/4/19.
 */

public interface ILoginView {

    void clearEditText();

    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    String getUsername();

    String getPassword();

    void loginSuccess();
}
