package com.qianwang.mvplogin.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.qianwang.mvplogin.R;
import com.qianwang.mvplogin.presenter.LoginPresenter;
import com.qianwang.mvplogin.view.IView.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener{

    private EditText et_username;
    private EditText et_pwd;
    private Button btn_cancel;
    private Button btn_login;
    private LoginPresenter mLoginPresent;
    private ProgressBar progressbar;

    private void initView() {

        et_username = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_login = (Button) findViewById(R.id.btn_login);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);

        btn_cancel.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        mLoginPresent=new LoginPresenter(this);
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(getContext(),"登录成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFailed(int status, String msg) {
        Toast.makeText(getContext(),"登录失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void usernameError(String errorMsg) {
        et_username.setError("用户名输入错误");
        et_username.requestFocus();
    }

    @Override
    public void phoneError(String errorMsg) {

    }

    @Override
    public void passwordError(String errorMsg) {
          et_pwd.setError("密码输入错误");
    }

    @Override
    public void verifyCodeError(String errorMsg) {

    }

    @Override
    public void verifyCodeFailed(String errorMsg) {

    }

    @Override
    public void verifyCodeSuccess(int reaskDuration, int expireDuration) {

    }

    @Override
    public void showLoading() {
          progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoading() {
        progressbar.setVisibility(View.GONE);

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showMsg(int msgId) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onClick(View view) {

    }
}
