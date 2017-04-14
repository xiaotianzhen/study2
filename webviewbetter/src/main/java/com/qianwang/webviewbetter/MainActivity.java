package com.qianwang.webviewbetter;



import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private LinearLayout web_view;
    private WebView webView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web_view = (LinearLayout) findViewById(R.id.web_view);
        webView = new WebView(this);

        web_view.addView(webView);
        webView.loadUrl("http://www.baidu.com");

        WebSettings webSetting = webView.getSettings();  //获得webView设置
        webSetting.setUseWideViewPort(true);         //可任意比例缩放
        webSetting.setLoadWithOverviewMode(true);   //适配
        webSetting.setJavaScriptEnabled(true);     //支持js
        webSetting.setCacheMode(WebSettings.LOAD_DEFAULT);    //设置缓存模式
        webSetting.setDomStorageEnabled(true);       //开启DOM storage API 功能
        webSetting.setDatabaseEnabled(true);         //开启database storage API 功能
        webSetting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);//HTTPS，注意这个是在LOLLIPOP以上才调用的
        webSetting.setAppCacheEnabled(true);        //开启 Application Caches 功能
        webSetting.setBlockNetworkImage(false);      //关闭加载网络图片，再一开始加载的时候可以设置为true


        /**
         *
         * web浏览器相关的
         */
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //加载进度
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                //获取webview标题
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
                //js 弹框
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {

                new AlertDialog.Builder(MainActivity.this).setTitle("提示").setMessage("你确定吗")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                result.confirm();
                                Toast.makeText(getApplicationContext(), "你点击了确定", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        result.cancel();
                    }
                }).show();

                return true;
            }
        });
        /**
         *
         * webview相关的
         */

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //加载完成
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                //设置在当前页面显示，而不是跳转到浏览器显示
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                //加载错误
            }
        });
        /**
         * 下载监听
         */
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {

            }
        });
        /**
         *
         * webView与js的交互
         */

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(webView!=null){
           webView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(webView!=null){
            webView.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(webView!=null){
            webView.clearCache(true);  //清除缓存
            /**
             *
             * 如果低于5.0版本的WebView中，如果先在parent中remove了WebView，
             * 那WebView将无法进行destroy了，这样就会造成内存的泄漏
             *
             * LOLLIPOP  android 5.0
             */
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){

                if(web_view!=null){
                    web_view.removeView(webView);
                }
                webView.removeAllViews();
                webView.destroy();

            }else {
                webView.removeAllViews();
                webView.destroy();
                if(web_view!=null){
                    web_view.removeView(webView);
                }
            }
            webView=null;
        }
    }
}
