package com.self.core.imagehelper;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


/**
 * Created by paragsarkar on 19/10/17.
 */

public class LoadImage {

    public static void showImageWithHolder(Context context, ImageView imageView, String imageUrl, int holderImage) {

        Glide.with(context).load(imageUrl)
                //.thumbnail(0.5f)
                //.crossFade()//.error(R.drawable.ic_loading_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(holderImage)
                .into(imageView);


    }

    public static void showImageSimple(Context context, ImageView imageView, String imageUrl, int errorImage) {

        try {
            if (context != null) {
                Glide.with(context).load(imageUrl)
                        //.thumbnail(0.5f).crossFade()
                        .error(errorImage)
                        //.placeholder(R.drawable.img_loading_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void showImageThumbnailRequest(Context context, ImageView imageView, String imageUrl) {

        // Setup Glide request without the into() method
        DrawableRequestBuilder<String> thumbnailRequest = Glide
                .with(context)
                .load(imageUrl);

        // Pass the request as a a parameter to the thumbnail request
        Glide.with(context)
                .load(imageUrl)
                .thumbnail(thumbnailRequest)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }


    public static void showImageWithProgress(Context context, ImageView imageView, String imageUrl,
                                             final ProgressBar progressBar, int errorImage) {

        Glide.with(context).load(imageUrl).error(errorImage)//.animate(R.anim.zoom_in)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model,
                                               Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model,
                                                   Target<GlideDrawable> target, boolean isFromMemoryCache,
                                                   boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);

                        return false;
                    }
                })
                //.thumbnail(0.5f)
                .crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    public static void showImageWithProgressAndHolder(Context context, ImageView imageView, String imageUrl,
                                                      final ProgressBar progressBar, int holderImage) {

        Glide.with(context).load(imageUrl)//.animate(R.anim.zoom_in)//.error(R.drawable.ic_loading_image)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model,
                                               Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model,
                                                   Target<GlideDrawable> target, boolean isFromMemoryCache,
                                                   boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);

                        return false;
                    }

                })
                //.thumbnail(0.5f)
                .crossFade().diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(holderImage).into(imageView);
    }


   /* public static void showImageWithAnimation(Context context, ImageView imageView, String imageUrl) {

        Glide.with(context).load(imageUrl)
                .animate(R.anim.zoom_in) // or android.R.anim.slide_in_left
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }*/


/*
    public static void showImageThumbnail(Context context, ImageView imageView, String imageUrl, int errorImage) {

        // Setup Glide request without the into() method
        DrawableRequestBuilder<String> thumbnailRequest = Glide
                .with(context)
                .load(imageUrl);

        Glide.with(context).load(imageUrl)
                .thumbnail(thumbnailRequest).crossFade()
                .animate(R.anim.zoom_in)
                .error(errorImage)
                //.placeholder(R.drawable.img_user_dummy)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);


    }
*/


    public static void showImageThumbnailWithProgress(Context context, ImageView imageView, String imageUrl,
                                                      final ProgressBar progressBar) {

        // Setup Glide request without the into() method
        DrawableRequestBuilder<String> thumbnailRequest = Glide
                .with(context)
                .load(imageUrl);

        // Pass the request as a a parameter to the thumbnail request
        Glide.with(context)
                .load(imageUrl)
                //.thumbnail(thumbnailRequest).error(R.drawable.img_loading_image_small)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model,
                                               Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model,
                                                   Target<GlideDrawable> target, boolean isFromMemoryCache,
                                                   boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);

                        return false;
                    }

                })
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }


    public static void showAsBitmap(Context context, ImageView imageView, String imageUrl,
                                    int errorImage, final ProgressBar progressBar) {

        Glide.with(context)
                .load(imageUrl).asBitmap().listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {

                progressBar.setVisibility(View.GONE);

                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target,
                                           boolean isFromMemoryCache, boolean isFirstResource) {

                progressBar.setVisibility(View.GONE);

                return false;
            }

        })
                .error(errorImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

    }
}
