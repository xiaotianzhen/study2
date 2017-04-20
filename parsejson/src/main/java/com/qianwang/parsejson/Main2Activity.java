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
import java.net.Proxy;
import java.net.URL;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL("http://192.168.1.100/test/weather.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    int code = conn.getResponseCode();

                    if (code == 200) {

                        InputStream in = conn.getInputStream();
                        InputStreamReader reader = new InputStreamReader(in);
                        BufferedReader bReader = new BufferedReader(reader);
                        String line = "";
                        StringBuffer buffer = new StringBuffer();

                        while ((line = bReader.readLine()) != null) {

                            buffer.append(line.trim());
                        }
                        Gson gson = new Gson();
                        Type type = new TypeToken<Weather>() {
                        }.getType();
                        Weather weather = gson.fromJson(buffer.toString(), type);
                        Log.i("520it", "Temperature" + "**************************"+weather.getResults().get(0).getWeather_data().get(0).getTemperature());
                        Log.i("520it", "buffer" + "**************************" + buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
