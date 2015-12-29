package com.win.network2.adapters;

import android.app.Application;
import android.graphics.Bitmap;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * author：WangShuang
 * Date: 2015/12/29 11:47
 * email：m15046658245_1@163.com
 */
public class BaseApplication  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

     Picasso picasso=   new Picasso.Builder(this)
                .memoryCache(new LruCache(10<<20))
                .downloader(new OkHttpDownloader(getCacheDir(),30<<20))
                .defaultBitmapConfig(Bitmap.Config.RGB_565)
                .build();

        Picasso.setSingletonInstance(picasso);
    }
}
