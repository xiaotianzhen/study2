package com.qianwang.fresco;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class BaseUseActivity extends AppCompatActivity {

    private SimpleDraweeView image_view1;
    private SimpleDraweeView image_view2;
    private SimpleDraweeView image_view3;
    private SimpleDraweeView image_view4;
    private SimpleDraweeView image_view5;
    private SimpleDraweeView image_view6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_use);

        image_view1= (SimpleDraweeView) findViewById(R.id.image_view1);
        image_view2= (SimpleDraweeView) findViewById(R.id.image_view2);
        image_view3= (SimpleDraweeView) findViewById(R.id.image_view3);
        image_view4= (SimpleDraweeView) findViewById(R.id.image_view4);
        image_view5= (SimpleDraweeView) findViewById(R.id.image_view5);
        image_view6= (SimpleDraweeView) findViewById(R.id.image_view6);


        String url="http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
        Uri uri=Uri.parse(url);
        image_view1.setImageURI(uri);
        image_view2.setImageURI(uri);
        image_view3.setImageURI(uri);
        image_view4.setImageURI(uri);
        image_view5.setImageURI(uri);
        image_view6.setImageURI(uri);


      /*  image_view1.setController(Fresco.newDraweeControllerBuilder()
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                        // 所有图片都加载成功时触发的方法
                        int width = imageInfo.getWidth();
                        int height = imageInfo.getHeight();
                        QualityInfo qualityInfo = imageInfo.getQualityInfo();
                        int quality = qualityInfo.getQuality();
                        boolean isOfFullQuality = qualityInfo.isOfFullQuality();
                        boolean isOfGoodEnoughQuality = qualityInfo.isOfGoodEnoughQuality();
                    }

                    @Override
                    public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
                        // 加载渐进式图片时回调的方法
                    }

                    @Override
                    public void onFailure(String id, Throwable throwable) {
                        // 加载图片失败时回调的方法
                    }
                })
                .setUri("http://image5.tuku.cn/pic/wallpaper/fengjing/menghuandaziranmeijingbizhi/009.jpg")
                .build());


       // 加载渐进式图片
        image_view1.setController(Fresco.newDraweeControllerBuilder()
                .setImageRequest(ImageRequestBuilder.newBuilderWithSource(
                        Uri.parse("http://image5.tuku.cn/pic/wallpaper/fengjing/menghuandaziranmeijingbizhi/009.jpg"))
                        .setProgressiveRenderingEnabled(true)
                        .build())
                .setOldController(image_view1.getController())
                .build());*/
    }
}
