package com.prituladima.android.redit.dagger;

import com.prituladima.android.redit.RedditApplication;
import com.prituladima.android.redit.model.Repository;
import com.prituladima.android.redit.presenter.RedditPresenter;
import com.prituladima.android.redit.view.RedditActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ContextModule.class,
                ApiModule.class
        }
)
public interface Injector {

    void inject(RedditApplication keeperApplication);

    void inject(RedditActivity redditActivity);

    void inject(RedditPresenter redditPresenter);

    void inject(Repository repository);

}