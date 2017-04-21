package com.qianwang.mvplogin.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianwang.mvplogin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DaliFragment extends Fragment {


    public DaliFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dali, container, false);
    }

}
