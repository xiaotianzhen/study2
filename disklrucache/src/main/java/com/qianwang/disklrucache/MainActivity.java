package com.qianwang.disklrucache;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.qianwang.disklrucache.libcore.io.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    DiskLruCache diskLruCache = null;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImage = (ImageView) findViewById(R.id.mImage);

        File cacheFile = getDiskCacheDir(getApplicationContext(), "bitmap");

        if (!cacheFile.exists()) {
            cacheFile.mkdirs();
        }
        try {
            diskLruCache = DiskLruCache.open(cacheFile, getAppVersion(getApplicationContext()), 1, 10 * 1024 * 1024);//10M
            writeCache();
            readCache();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //额外的api   size(); 缓存大小  delete();清除所有缓存



    }

    /**
     * 读取缓存
     */

    public void readCache() {

        String imgUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
        String key = hashKeyForDisk(imgUrl);
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
            InputStream in = snapshot.getInputStream(0);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            mImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入缓存
     */

    public void writeCache() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String imgUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
                    String key = hashKeyForDisk(imgUrl);
                    DiskLruCache.Editor editor = diskLruCache.edit(key);
                    if (editor != null) {

                        if (downloadUrlToStream(imgUrl, editor.newOutputStream(0))) {
                            editor.commit(); //提交写入
                            Log.i("520it", "" + "**********  写入成功  ****************");
                        } else {
                            Log.i("520it", "" + "**********  写入失败 ****************");
                            editor.abort();  //放弃本次写入
                        }
                    }
                    //但其实并不是每次写入缓存都要调用一次flush()方法的，频繁地调用并不会带来任何好处，只会额外增加同步journal文件的时间。
                    // 比较标准的做法就是在Activity的onPause()方法中去调用一次flush()方法就可以了。
                    diskLruCache.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     *
     * 移除缓存  只有你确定某个key对应的缓存内容已经过期，需要从网络获取最新数据的时候才应该调用remove()方法来移除缓存。
     * 因为我们设置了缓存的大小值
     *
     */
    public void removecache(){

        try {
            String imgUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
            String key = hashKeyForDisk(imgUrl);
            diskLruCache.remove(key);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     *
     * 获得缓存目录
     * @param context
     * @param uniqueName
     * @return
     */

    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 获得应用版本号
     * @param context
     * @return
     * @throws PackageManager.NameNotFoundException
     */

    public int getAppVersion(Context context) throws PackageManager.NameNotFoundException {

        PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);

        return info.versionCode;
    }

    public boolean downloadUrlToStream(String url, OutputStream outputStream) {

        HttpURLConnection conn = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;

        try {
            URL path = new URL(url);
            conn = (HttpURLConnection) path.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            int code = conn.getResponseCode();

            if (code == 200) {
                in = new BufferedInputStream(conn.getInputStream(), 8 * 1024);
                out = new BufferedOutputStream(outputStream, 8 * 1024);
                int len = 0;
                while ((len = in.read()) != -1) {

                    out.write(len);
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }

            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * url 转md5    key作为缓存的文件名，缓存文件的名字必须是url，所以用md5
     *
     * @param key
     * @return
     */

    public String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            diskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            diskLruCache.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
