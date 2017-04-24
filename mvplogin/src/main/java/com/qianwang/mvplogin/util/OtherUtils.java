package com.qianwang.mvplogin.util;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by sky on 2017/4/24.
 */

public class OtherUtils  {

    public static void startTimer(final WeakReference<TextView> tvVerifyCode, final String defaultString, int max, int interval){

        tvVerifyCode.get().setEnabled(false);
        new CountDownTimer(max * 1000, interval * 1000 - 10) {
            @Override
            public void onTick(long time) {
                if(tvVerifyCode.get()==null){
                    this.cancel();
                } else {
                    tvVerifyCode.get().setText(""+(time+15)/1000+"s");
                }
            }

            @Override
            public void onFinish() {

                if(tvVerifyCode.get()==null){
                    this.cancel();
                    return;
                }
                tvVerifyCode.get().setEnabled(true);
                tvVerifyCode.get().setText(defaultString);
            }
        }.start();
    }
}
