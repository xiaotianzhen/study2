package com.qianwang.fresco.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.qianwang.fresco.util.ImageLoaderConfig;

/**
 * Created by sky on 2017/4/22.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(getApplicationContext(), ImageLoaderConfig.getImagePipelineConfig(getApplicationContext()));
    }
}
