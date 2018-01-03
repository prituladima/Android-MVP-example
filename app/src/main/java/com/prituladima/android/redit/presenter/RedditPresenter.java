package com.prituladima.android.redit.presenter;

import com.prituladima.android.redit.RedditApplication;
import com.prituladima.android.redit.arch.BasePresenter;
import com.prituladima.android.redit.arch.RedditTopContract;
import com.prituladima.android.redit.model.api.RedditApi;
import com.prituladima.android.redit.util.Logger;
import com.prituladima.android.redit.util.Mappers;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.HttpException;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Singleton
public class RedditPresenter extends BasePresenter<RedditTopContract.RedditTopView> implements RedditTopContract.IRedditPresenter {

    private static Logger LOGGER = new Logger(RedditPresenter.class);

    private Subscription subscription;

    @Inject
    RedditApi redditApi;

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
    }

    @Override
    public void syncAndUpdateView() {
        subscription = redditApi.getPage(50, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(Mappers.getRedditMapper())
                .subscribe(
                        (list) -> getMvpView().onUpdateData(list),
                        (throwable) -> {
                            LOGGER.error(throwable);
                            if (throwable instanceof UnknownHostException)
                                getMvpView().onNoInternetError();
                            else if (throwable instanceof HttpException || throwable instanceof SocketTimeoutException)
                                getMvpView().onServerError();
                        }
                );
    }

    @Override
    public void syncAndSuggestToUpdateView() {

    }

    @Override
    public void getAndUpadateView() {

    }
}