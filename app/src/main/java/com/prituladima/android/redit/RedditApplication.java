package com.prituladima.android.redit;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.orhanobut.hawk.Hawk;
import com.prituladima.android.redit.dagger.ContextModule;
import com.prituladima.android.redit.dagger.DaggerInjector;
import com.prituladima.android.redit.dagger.Injector;
import com.prituladima.android.redit.model.db.HawkAutoValueParser;
import com.prituladima.android.redit.util.Logger;

public class RedditApplication extends Application {

    private Logger LOGGER = new Logger(RedditApplication.class);

    public static Injector getInjector() {
        return injector;
    }

    private static Injector injector;

    @Override
    public void onCreate() {
        super.onCreate();
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

        Hawk.init(this)
                .setParser(new HawkAutoValueParser())
                .setLogInterceptor((message) -> LOGGER.log(message))
                .build();

    }

}