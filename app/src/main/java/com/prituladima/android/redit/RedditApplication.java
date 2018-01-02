package com.prituladima.android.redit;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.prituladima.android.redit.dagger.ContextModule;
import com.prituladima.android.redit.dagger.DaggerInjector;
import com.prituladima.android.redit.dagger.Injector;

public class RedditApplication extends Application {

    public static RedditApplication getInstance() {
        return redditApplication;
    }

    public static Injector getInjector() {
        return getInstance().injector;
    }

    private static Injector injector;
    private static RedditApplication redditApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        redditApplication = this;
        injector = DaggerInjector
                .builder()
                .contextModule(new ContextModule(this))
                .build();

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(defaultOptions)
                .build();

        ImageLoader.getInstance().init(config);

    }

}