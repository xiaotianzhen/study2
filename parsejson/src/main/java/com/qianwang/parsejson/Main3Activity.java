package com.qianwang.parsejson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url=new URL("http://192.168.1.100/test/news.php");
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);

                    int code=conn.getResponseCode();
                    if(code==200){
                        InputStream in=conn.getInputStream();
                        InputStreamReader reader=new InputStreamReader(in);
                        BufferedReader bRead=new BufferedReader(reader);
                        String line="";
                        StringBuilder sb=new StringBuilder();
                        while((line=bRead.readLine())!=null){
                            sb.append(line.trim());
                        }
                        Log.i("520it", "sb" + "**************************"+sb.toString());

                        Gson gson=new Gson();
                        Type type=new TypeToken<News>(){}.getType();
                        News news=gson.fromJson(sb.toString(),type);

                        Log.i("520it", "news" + "**************************"+  news.getShowapi_res_body().getPagebean().getContentlist().get(0).getChannelName());

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
