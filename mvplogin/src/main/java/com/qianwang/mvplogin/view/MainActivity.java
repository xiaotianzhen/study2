package com.qianwang.mvplogin.view;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qianwang.mvplogin.R;
import com.qianwang.mvplogin.presenter.MainPresenter;
import com.qianwang.mvplogin.view.IView.IMainView;

public class MainActivity extends AppCompatActivity  implements IMainView {

    private MainPresenter mMainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainPresenter=new MainPresenter(this,this);
        mMainPresenter.initFragment();

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

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

}
