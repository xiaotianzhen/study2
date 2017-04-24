package com.qianwang.acache;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.qianwang.acache.util.ACache;

import org.json.JSONException;
import org.json.JSONObject;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ACache aCache=ACache.get(TestActivity.this);
       JSONObject jsonObject= aCache.getAsJSONObject("user");
        try {
            String username=jsonObject.getString("username");
            Log.i("520it", "username" + "**************************"+username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
