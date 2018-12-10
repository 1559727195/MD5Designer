package com.massky.md5designer.module.image;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.channels.FileChannel;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

public class ImageLoader {

    private static VignetteFilterTransformation VIGNETTE_TRANSFORMATION = new VignetteFilterTransformation();


    public static void load(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .into(imageView);
    }


    public static void loadNoCrop(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .into(imageView);
    }

    public static void load(Context context, String url, @DrawableRes int placeholderId, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .placeholder(placeholderId)
                .into(imageView);
    }

    public static void download(Context context, String url, final OnDownloadCallback callback) {
        GlideApp.with(context).downloadOnly().load(url).into(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                if (callback != null) {
                    callback.download(resource);
                }
            }
        });
    }

    public static void downloadFile(final Context context, String url, final File saveFile, final OnFileCallback callback) {
        Glide.with(context).asFile().load(url)
                .listener(new FileRequestListener())
                .into(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                        FileInputStream fis = null;
                        FileOutputStream fos = null;
                        try {
                            fis = new FileInputStream(resource);
                            fos = new FileOutputStream(saveFile);
                            FileChannel inChannel = fis.getChannel();
                            FileChannel outChannel = fos.getChannel();
                            inChannel.transferTo(0, inChannel.size(), outChannel);
                            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(saveFile)));
                            if (callback != null) {
                                callback.onFile(true);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            if (callback != null) {
                                callback.onFile(false);
                            }
                        } finally {
                            if (fis != null) {
                                try {
                                    fis.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (fos != null) {
                                try {
                                    fos.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
    }

    @SuppressWarnings("all")
    public static void loadWithVignette(Context context, String url, @DrawableRes int placeholderId, ImageView imageView) {
        Single.just(placeholderId)
                .observeOn(Schedulers.io())
                .map(id -> {
                    GPUImageVignetteFilter filter = new GPUImageVignetteFilter();
                    filter.setVignetteCenter(new PointF(0.5f, 0.5f));
                    filter.setVignetteColor(new float[]{0.0f, 0.0f, 0.0f});
                    filter.setVignetteStart(0.0f);
                    filter.setVignetteEnd(0.75f);
                    GPUImage gpuImage = new GPUImage(context);
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) ContextCompat.getDrawable(context, id);
                    gpuImage.setImage(bitmapDrawable.getBitmap());
                    gpuImage.setFilter(filter);
                    return new BitmapDrawable(context.getResources(), gpuImage.getBitmapWithFilterApplied());

                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Drawable>() {
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable disposable) {
                        this.disposable = disposable;
                    }

                    @Override
                    public void onSuccess(Drawable drawable) {
                        GlideApp.with(context)
                                .load(url)
                                .placeholder(drawable)
                                .transform(VIGNETTE_TRANSFORMATION)
                                .dontAnimate()
                                .format(DecodeFormat.PREFER_ARGB_8888)
                                .into(imageView);
                        if (disposable != null && !disposable.isDisposed()) {
                            disposable.dispose();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        GlideApp.with(context)
                                .load(url)
                                .placeholder(placeholderId)
                                .transform(VIGNETTE_TRANSFORMATION)
                                .dontAnimate()
                                .format(DecodeFormat.PREFER_ARGB_8888)
                                .into(imageView);
                        if (disposable != null && !disposable.isDisposed()) {
                            disposable.dispose();
                        }
                    }
                });
    }

    public interface OnDownloadCallback {
        void download(File file);
    }

    public interface OnFileCallback {
        void onFile(boolean isSuccess);
    }


    private static class FileRequestListener implements RequestListener<File> {

        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
            target.onResourceReady(new File(URI.create(model.toString())), null);
            return false;
        }

        @Override
        public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
            return false;
        }
    }


}
