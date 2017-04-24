package com.qianwang.acache;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qianwang.acache.util.ACache;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //缓存JSONObject

        ACache aCache=ACache.get(MainActivity.this);
        JSONObject jsonObject=new JSONObject();

        try {
            jsonObject.put("username","zhangsan");
            jsonObject.put("password","123456");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        aCache.put("user",jsonObject);

        Intent intent=new Intent(MainActivity.this,TestActivity.class);
        startActivity(intent);
        finish();
    }
}
