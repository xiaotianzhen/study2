package com.qianwang.mvplogin.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianwang.mvplogin.R;
import com.qianwang.mvplogin.presenter.LiveMainPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveFragment extends Fragment {

    LiveMainPresenter mLiveMainPresenter;
    public LiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        mLiveMainPresenter=new LiveMainPresenter(new MainActivity(),this);
        mLiveMainPresenter.initView(view);
        mLiveMainPresenter.initViewpage();
    }
}
