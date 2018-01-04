package com.prituladima.android.redit.presenter;

import com.prituladima.android.redit.RedditApplication;
import com.prituladima.android.redit.arch.BasePresenter;
import com.prituladima.android.redit.arch.RedditTopContract;
import com.prituladima.android.redit.model.Repository;
import com.prituladima.android.redit.util.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Subscription;

@Singleton
public class RedditPresenter extends BasePresenter<RedditTopContract.RedditTopView> implements RedditTopContract.IRedditPresenter {

    private static Logger LOGGER = new Logger(RedditPresenter.class);

    private Subscription subscription;

    @Inject
    Repository repository;

    @Inject
    public RedditPresenter() {
        RedditApplication.getInjector().inject(this);
    }

    @Override
    public void attachView(RedditTopContract.RedditTopView redditTopView) {
        super.attachView(redditTopView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        repository.resetCounter();
    }

    @Override
    public void getRedditTop(boolean refresh) {
        if (refresh) repository.resetCounter();
        subscription = repository.getRedditTop()
                .subscribe(
                        (list) -> {
                            if (refresh) {
                                if (list.isEmpty())
                                    getMvpView().onNoDataAvailable();
                                else
                                    getMvpView().onUpdateData(list);
                            } else {
                                if (list.isEmpty())
                                    getMvpView().onStopLoading();
                                else
                                    getMvpView().onAddData(list);
                            }
                        }
                );
    }
}