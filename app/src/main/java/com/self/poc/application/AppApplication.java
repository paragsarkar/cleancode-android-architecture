package com.self.poc.application;

import android.app.Application;
import android.graphics.Bitmap;

import com.self.poc.httpHandler.ImageCacheManager;
import com.self.poc.httpHandler.RequestManager;

/**
 * Created by paragsarkar on 28/10/17.
 */

public class AppApplication extends Application {

    private static int IMAGE_CACHE_SIZE = 1024 * 1024 * 10; // 10 MB
    private static Bitmap.CompressFormat DISK_IMAGE_CACHE_COMPRESS_FORMAT = Bitmap.CompressFormat.PNG;
    private static int DISK_IMAGE_CACHE_QUALITY = 100; // PNG is loss-less

    @Override
    public void onCreate() {
        super.onCreate();
        RequestManager.init(getApplicationContext());
        ImageCacheManager.init(getApplicationContext(),
                this.getPackageCodePath(),
                IMAGE_CACHE_SIZE,
                DISK_IMAGE_CACHE_COMPRESS_FORMAT,
                DISK_IMAGE_CACHE_QUALITY,
                ImageCacheManager.CacheMode.DISK);
    }
}
