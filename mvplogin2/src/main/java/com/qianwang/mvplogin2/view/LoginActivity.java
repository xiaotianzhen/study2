package com.qianwang.mvplogin2.view;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.qianwang.mvplogin2.R;
import com.qianwang.mvplogin2.presenter.LoginPresent;
import com.qianwang.mvplogin2.view.IView.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {


    private EditText et_username;
    private EditText et_pwd;
    private Button btn_cancel;
    private Button btn_login;
    private LoginPresent mLoginPresent;
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
        mLoginPresent = new LoginPresent(this);


    }

    @Override
    public void clearEditText() {
        et_username.setText("");
        et_pwd.setText("");
    }

    @Override
    public void showProgress() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        et_username.setError("用户名错误");
        et_username.requestFocus();
    }

    @Override
    public void setPasswordError() {
        et_pwd.setError("密码错误");
        et_pwd.requestFocus();
    }

    @Override
    public String getUsername() {
        return et_username.getText().toString();
    }

    @Override
    public String getPassword() {
        return et_pwd.getText().toString();
    }

    @Override
    public void loginSuccess() {

        Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.btn_cancel:
                mLoginPresent.clear();
                break;

            case R.id.btn_login:
                mLoginPresent.doLogin(et_username.getText().toString(), et_pwd.getText().toString());
                break;

        }
    }

    @Override
    protected void onDestroy() {

        mLoginPresent.onDestory();
        super.onDestroy();
    }
}
