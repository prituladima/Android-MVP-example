package com.prituladima.android.redit.dagger;

import com.prituladima.android.redit.model.api.RedditApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {

    @Provides
    @Singleton
    public RedditApi providesAPI() {
        return new RedditApi.Factory().makeService();
    }

}