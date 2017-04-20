package com.qianwang.parsejson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.qianwang.parsejson.Response.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    URL url = new URL("http://192.168.1.100/test/json.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    int code = conn.getResponseCode();
                    Log.i("521it", "code" + "**************************" + code);
                    if (code == 200) {

                        InputStream in = conn.getInputStream();
                        InputStreamReader isr = new InputStreamReader(in);
                        BufferedReader reader = new BufferedReader(isr);
                        StringBuilder sb = new StringBuilder();
                        String line = "";

                        while ((line = reader.readLine()) != null) {

                            sb.append(line.trim());
                        }

                        /**
                         *
                         * gson解析
                         */
                        Gson gson = new Gson();
                        Type type = new TypeToken<Response>() {
                        }.getType();
                        Response response = gson.fromJson(sb.toString().trim(), type);
                        Log.i("520it", "response" + "**************************" + response.getResponse_head().getRespmenu());


                        /**
                         *
                         * JSONObject/JSONArray
                         */

                        Response response1 = new Response();
                        Response_head head = new Response_head();
                        Response_body body=new Response_body();
                        Log.i("521it", "result" + "**************************" + sb.toString().trim());
                        JSONObject jsonObject = new JSONObject(sb.toString().trim());
                        JSONObject response_head = jsonObject.getJSONObject("response_head");
                        head.setRespmenu(response_head.getString("respmenu"));
                        head.setResptime(response_head.getString("resptime"));
                        JSONObject respinfo = response_head.getJSONObject("respinfo");
                        Response_head.Respinfo respinfo1 = new Response_head.Respinfo();
                        respinfo1.setRespcode(respinfo.getString("respcode"));
                        respinfo1.setRespdes(respinfo.getString("respdes"));

                        Log.i("520it", "respcode" + "**************************" + respinfo1.getRespcode());
                        Log.i("520it", "respdes" + "**************************" + respinfo1.getRespdes());

                        JSONObject response_body = jsonObject.getJSONObject("response_body");
                        JSONArray crset = response_body.getJSONArray("crset");


                        for (int i = 0; i < crset.length(); i++) {


                            JSONObject menu = crset.getJSONObject(i);
                            JSONArray menuArray = menu.getJSONArray("menu");

                            Response_body.Merchant merchant = new Response_body.Merchant();
                            merchant.setMerchantname(crset.getJSONObject(i).getString("merchantname"));
                            Log.i("520it", "merchantname" + "**************************"+crset.getJSONObject(i).getString("merchantname"));

                            for (int n = 0; n < menuArray.length(); n++) {

                                List<Response_body.Merchant.Menu> menus = new ArrayList<Response_body.Merchant.Menu>();
                                Response_body.Merchant.Menu menu1 = new Response_body.Merchant.Menu();
                                menu1.setMenuid(menuArray.getJSONObject(n).getString("menuid"));
                                menu1.setMenuname(menuArray.getJSONObject(n).getString("menuname"));
                                menus.add(menu1);

                                Log.i("520it", "menuid" + "**************************" + menu1.getMenuid());
                                Log.i("520it", "menuname" + "**************************" + menu1.getMenuname());
                                merchant.setMenu(menus);
                                List<Response_body.Merchant> list=new ArrayList<Response_body.Merchant>();
                                list.add(merchant);
                                body.setCrset(list);
                            }
                        }
                        response1.setResponse_head(head);
                        response1.setResponse_body(body);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
