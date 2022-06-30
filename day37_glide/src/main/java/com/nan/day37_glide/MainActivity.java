package com.nan.day37_glide;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image_view);
    }

    public void loadImage(View view) {
        String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        String gifUrl = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.huabanimg.com%2F57cb25a626e23602d79aa590e3900a0ac95601516dfe7c-3uk92S_fw658&refer=http%3A%2F%2Fhbimg.huabanimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1658657593&t=7f4355437a5144117a02ad0bc905a561";
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_foreground)
                //.diskCacheStrategy(DiskCacheStrategy.NONE)
                .transform(new BlurTransformation(20));
        Glide.with(this)
                .asBitmap()
                .load(gifUrl)
                .apply(options)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);
    }

    public void loadImage1(View view) {
        String url = "http://guolin.tech/book.png";
        Glide.with(this)
                .load(url)
                .into(imageView);
    }
}