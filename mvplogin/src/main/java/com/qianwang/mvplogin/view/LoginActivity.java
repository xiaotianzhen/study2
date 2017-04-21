package com.qianwang.mvplogin.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.qianwang.mvplogin.R;
import com.qianwang.mvplogin.presenter.LoginPresenter;
import com.qianwang.mvplogin.view.IView.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    private EditText et_username;
    private EditText et_pwd;
    private Button btn_register;
    private Button btn_login;
    private Button btn_phoneLogin;
    private LoginPresenter mLoginPresent;
    private ProgressBar progressbar;
    //手机验证登陆控件
    private TextView tvVerifyCode;
    private boolean isPhoneLogin;
    private String verifyCode;

    private void initView() {

        et_username = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_phoneLogin = (Button) findViewById(R.id.btn_phoneLogin);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        tvVerifyCode = (TextView) findViewById(R.id.tvVerifyCode);

        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_phoneLogin.setOnClickListener(this);
        tvVerifyCode.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        mLoginPresent = new LoginPresenter(this);
        userNameLoginViewInit();
    }

    /**
     * 手机登录初始化
     */

    public void userNameLoginViewInit() {

        isPhoneLogin = false;
        tvVerifyCode.setVisibility(View.GONE);
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(2000);
        tvVerifyCode.setAnimation(animation);
        et_username.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        et_username.setText("");
        et_pwd.setText("");
        btn_phoneLogin.setText("手机号登录");
        et_username.setHint("请输入用户名");
        et_pwd.setHint("请输入密码");

        btn_phoneLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneLoginViewInit();
            }
        });


    }

    public void phoneLoginViewInit() {
        isPhoneLogin = true;
        tvVerifyCode.setVisibility(View.VISIBLE);
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(2000);
        tvVerifyCode.setAnimation(animation);
        et_username.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        et_username.setText("");
        et_pwd.setText("");
        btn_phoneLogin.setText("用户登录");
        et_username.setHint("请输入手机号");
        et_pwd.setHint("请输入验证码");

        btn_phoneLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNameLoginViewInit();
            }
        });
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFailed(int status, String msg) {
        Toast.makeText(getContext(), status+msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void usernameError(String errorMsg) {
        et_username.setError(errorMsg);
        et_username.requestFocus();
    }

    @Override
    public void phoneError(String errorMsg) {
        et_username.setError(errorMsg);
        et_username.requestFocus();
    }

    @Override
    public void passwordError(String errorMsg) {

        et_pwd.setError(errorMsg);
    }

    @Override
    public void verifyCodeError(String errorMsg) {

        showMsg(errorMsg);
    }

    @Override
    public void verifyCodeFailed(String errorMsg) {
        showMsg(errorMsg);
    }

    @Override
    public void verifyCodeSuccess(int reaskDuration, int expireDuration) {
        showMsg("注册短信下发，验证码 " + expireDuration / 60 + " 分钟内有效！");
    }

    @Override
    public void showLoading() {
        showLoading(true);
    }

    @Override
    public void dismissLoading() {
        showLoading(false);

    }

    /**
     * 加载的时候界面控件的显示和隐藏
     *
     * @param ishow
     */

    private void showLoading(boolean ishow) {

        if (ishow) {
            progressbar.setVisibility(View.VISIBLE);
            btn_login.setEnabled(false);
            btn_phoneLogin.setEnabled(false);
            btn_register.setEnabled(false);
            et_pwd.setEnabled(false);
            et_username.setEnabled(false);

        } else {
            progressbar.setVisibility(View.GONE);
            btn_login.setEnabled(true);
            btn_phoneLogin.setEnabled(true);
            btn_register.setEnabled(true);
            et_pwd.setEnabled(true);
            et_username.setEnabled(true);

        }
    }


    @Override
    public void showMsg(String msg) {

        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMsg(int msgId) {
        showMsg(msgId);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.btn_login:

                if(isPhoneLogin){
                    mLoginPresent.phoneLogin(et_username.getText().toString(),et_pwd.getText().toString());
                }else {
                    mLoginPresent.userNameLogin(et_username.getText().toString(),et_pwd.getText().toString());
                }
                break;
            case R.id.tvVerifyCode:

                mLoginPresent.sendVerifyCode(et_username.getText().toString());

                final EditText editText=new EditText(getContext());
                new AlertDialog.Builder(getContext()).setTitle("验证码").setMessage("请输入短信获取到的验证码").setView(editText)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                verifyCode=editText.getText().toString();

                                et_pwd.setText(verifyCode);
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
                break;
            case R.id.btn_register:

                break;

        }
    }


    public String getDiskCacheDir(Context context){

        String cachePath="";
        //SD卡存在或者是不能sd卡不能别移除
        if(android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)||!Environment.isExternalStorageRemovable()){

            cachePath=context.getExternalCacheDir().getPath();   //android/data/  sd卡目录下的缓存文件
        }else {
            cachePath=context.getCacheDir().getPath();          //data/data/    系统目录下的缓存文件
        }


        return cachePath;
    }
}
