package com.qianwang.audiorecord;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.lqr.audio.AudioPlayManager;
import com.lqr.audio.AudioRecordManager;
import com.lqr.audio.IAudioPlayListener;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AudioRecordManager.getInstance(getApplicationContext()).setMaxVoiceDuration(1200);   //设置时长

        File audioFile = new File(Environment.getExternalStorageDirectory(), "AUDIO");

        if (!audioFile.exists()) {
            audioFile.mkdirs();
        }

        AudioRecordManager.getInstance(this).setAudioSavePath(audioFile.getAbsolutePath());

        //录音
        AudioRecordManager.getInstance(this).startRecord();
        AudioRecordManager.getInstance(this).willCancelRecord();
        AudioRecordManager.getInstance(this).continueRecord();
        AudioRecordManager.getInstance(this).stopRecord();
        AudioRecordManager.getInstance(this).destroyRecord();


        //播放录音

        AudioPlayManager.getInstance().startPlay(getApplicationContext(), audioUri, new IAudioPlayListener() {
            public void onStart(Uri var1) {
                //开播（一般是开始语音消息动画）
            }

            @Override
            public void onStop(Uri var1) {
                //停播（一般是停止语音消息动画）
            }

            @Override
            public void onComplete(Uri var1) {
                //播完（一般是停止语音消息动画）
            }
        });

        AudioPlayManager.getInstance().stopPlay();
    }
}
