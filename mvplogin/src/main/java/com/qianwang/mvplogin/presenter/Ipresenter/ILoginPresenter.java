package com.qianwang.mvplogin.presenter.Ipresenter;



/**
 * Created by sky on 2017/4/19.
 */

public abstract class ILoginPresenter implements BasePresenter  {



    @Override
    public void finish() {

    }

    @Override
    public void start() {

    }



    /**
     * 检查手机号验证码是否合法
     * @param phone
     * @param verifyCode
     * @return
     */
    public abstract boolean checkPhoneLogin(String phone, String verifyCode);


    /**
     * 检查用户名密码是否合法
     * @param userName
     * @param password
     * @return
     */
    public abstract boolean checkUserNameLogin(String userName, String password);

    /**
     * 手机号登录
     * @param phone
     * @param verifyCode
     */
    public abstract void phoneLogin(String phone, String verifyCode);

    /**
     * 用户名登录
     * @param userName
     * @param password
     */
    public abstract void userNameLogin(String userName, String password);

    /**
     * 发送验证码
     * @param phoneNum
     */
    public abstract void sendVerifyCode(String phoneNum);
}
