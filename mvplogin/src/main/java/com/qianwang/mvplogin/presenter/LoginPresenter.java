package com.qianwang.mvplogin.presenter;


import android.content.Intent;
import android.os.Handler;

import com.qianwang.mvplogin.model.UserInfo;
import com.qianwang.mvplogin.model.UserInfoCache;
import com.qianwang.mvplogin.presenter.Ipresenter.ILoginPresenter;
import com.qianwang.mvplogin.util.ACache;
import com.qianwang.mvplogin.util.NetUtils;
import com.qianwang.mvplogin.util.PhoneUtils;
import com.qianwang.mvplogin.view.IView.ILoginView;
import com.qianwang.mvplogin.view.MainActivity;

import java.text.AttributedCharacterIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Created by sky on 2017/4/19.
 */

public class LoginPresenter extends ILoginPresenter {
    private ILoginView mLoginView;

    public LoginPresenter(ILoginView loginView) {
        mLoginView = loginView;
    }

    public boolean checkPhoneLogin(String phone, String verifyCode) {

        if (PhoneUtils.isMobile(phone)) {

            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher matcher = pattern.matcher(verifyCode);
            if (matcher.matches()) {

                if (NetUtils.check(mLoginView.getContext())) {
                    return true;
                } else {
                    mLoginView.showMsg("当前网络不可用");
                }

            } else {
                mLoginView.verifyCodeError("验证码格式错误");
            }

        } else {
            mLoginView.phoneError("手机号格式错误");
        }

        return false;
    }

    @Override
    public boolean checkUserNameLogin(String userName, String password) {

        if (password.length() > 6) {

            if (NetUtils.check(mLoginView.getContext())) {

                return true;
            } else {
                mLoginView.usernameError("当前网络不可用");
            }
        } else {
            mLoginView.passwordError("密码错误");
        }
        return false;
    }

    @Override
    public void phoneLogin(String phone, final String verifyCode) {

        mLoginView.showLoading();
        //判断手机号和验证码是否有效

        if (checkPhoneLogin(phone, verifyCode)) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {


                    if (verifyCode.equals("123456")) {

                        mLoginView.loginSuccess();
                    } else {
                        mLoginView.loginFailed(404, "登录失败");
                    }

                }
            }, 2000);
        }
        mLoginView.dismissLoading();
    }

    @Override
    public void userNameLogin(final String userName, final String password) {
        mLoginView.showLoading();
        //判断用户名和密码是否有效
        if (checkUserNameLogin(userName, password)) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (userName.equals("dong") && password.equals("1234567")) {

//                        UserInfo info=new UserInfo();
//                        UserInfoCache.saveCache(mLoginView.getContext(),info);   //info 在实际工作中为，在登录后获得服务器返回的用户详细信息
                        ACache.get(mLoginView.getContext()).put("username","dong");
                        ACache.get(mLoginView.getContext()).put("password","1234567");
                        mLoginView.loginSuccess();
                    } else {
                        mLoginView.loginFailed(404, "登录失败");
                    }
                }
            }, 500);
        }
        mLoginView.dismissLoading();
    }

    @Override
    public void sendVerifyCode(String phoneNum) {

        if (PhoneUtils.isMobile(phoneNum)) {
            boolean success = true;
            if (success) {
                mLoginView.verifyCodeSuccess(60, 60);
            } else {
                mLoginView.verifyCodeFailed("验证失败");
            }
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void finish() {

    }

}
