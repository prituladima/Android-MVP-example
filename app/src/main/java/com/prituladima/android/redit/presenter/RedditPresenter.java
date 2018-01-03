package com.prituladima.android.redit.presenter;

import com.prituladima.android.redit.RedditApplication;
import com.prituladima.android.redit.arch.BasePresenter;
import com.prituladima.android.redit.arch.RedditTopContract;
import com.prituladima.android.redit.model.DataManager;
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
    DataManager dataManager;

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
        dataManager.resetCounter();
    }

    @Override
    public void syncAndUpdateView(boolean refresh) {
        if (refresh) dataManager.resetCounter();
        subscription = dataManager.sync()
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