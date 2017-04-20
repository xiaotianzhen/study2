package com.qianwang.mvplogin2.presenter;

import android.os.Handler;

import com.qianwang.mvplogin2.model.User;
import com.qianwang.mvplogin2.presenter.IPresenter.ILoginPresenter;
import com.qianwang.mvplogin2.view.IView.ILoginView;

/**
 * Created by sky on 2017/4/19.
 */

public class LoginPresent implements ILoginPresenter{

    private  ILoginView mILoginView;
    private User mUser;
    public LoginPresent(ILoginView loginView) {
        this.mILoginView=loginView;
        initUser();
    }

    public void initUser(){
        mUser=new User(mILoginView.getUsername(),mILoginView.getPassword());
    }
    @Override
    public void doLogin(String username, String password) {
         mILoginView.showProgress();
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 mILoginView.hideProgress();
                int code= mUser.checkUserValidity(mILoginView.getUsername(),mILoginView.getPassword());

                 if(code==-1){
                     mILoginView.setPasswordError();
                 }else {
                     mILoginView.loginSuccess();
                 }
             }
         },2000);
    }

    @Override
    public void clear() {
   mILoginView.clearEditText();
    }

    @Override
    public void onDestory() {
        mILoginView=null;
    }
}
