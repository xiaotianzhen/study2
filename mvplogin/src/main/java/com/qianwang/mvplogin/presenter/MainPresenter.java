package com.qianwang.mvplogin.presenter;

import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import com.qianwang.mvplogin.R;
import com.qianwang.mvplogin.presenter.Ipresenter.IMainPresenter;
import com.qianwang.mvplogin.view.IView.IMainView;
import com.qianwang.mvplogin.view.LiveFragment;
import com.qianwang.mvplogin.view.MainActivity;
import com.qianwang.mvplogin.view.PublishFragment;
import com.qianwang.mvplogin.view.UserInfoFragment;

/**
 * Created by sky on 2017/4/21.
 */

public class MainPresenter extends IMainPresenter {

    private FragmentTabHost mTabHost;
    private IMainView mIMainView;
    private Class[] fragments = {LiveFragment.class, PublishFragment.class, UserInfoFragment.class};
    private int[] imageIds = {R.drawable.tab_live_selector, R.drawable.tab_publish_selector, R.drawable.tab_my_selector};
    private String[] mStrings = {"live", "publish", "my"};
    private MainActivity mContext;

    public MainPresenter(IMainView mainView, MainActivity context) {

        this.mIMainView = mainView;
        this.mContext = context;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void finish() {
        super.finish();

        if (mIMainView != null) {
            mIMainView = null;
        }
    }

    @Override
    public void initFragment() {

        mTabHost = (FragmentTabHost) mContext.findViewById(R.id.tabhost);
        mTabHost.setup(mContext, mContext.getSupportFragmentManager(), R.id.contentPanel);
        //初始化 TabSpec，添加其相应数据
        int fragmentCount = fragments.length;
        for (int i=0;i<fragmentCount;i++){
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mStrings[i]).setIndicator(getTabItemView(i));
            Log.i("520it", "" + "**************************"+mStrings[i]);
            mTabHost.addTab(tabSpec,fragments[i],null);
            mTabHost.getTabWidget().setDividerDrawable(null);
        }
    }

    @Override
    protected View getTabItemView(int i) {

        //该布局只有一张图片，很简单，不贴代码
        View v = LayoutInflater.from(mContext).inflate(R.layout.tab_live,null);
        ImageView icon = (ImageView) v.findViewById(R.id.mImage);
        icon.setImageResource(imageIds[i]);
        return v;
    }
}
