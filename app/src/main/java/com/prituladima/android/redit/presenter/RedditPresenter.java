package com.prituladima.android.redit.presenter;

import com.prituladima.android.redit.RedditApplication;
import com.prituladima.android.redit.arch.BasePresenter;
import com.prituladima.android.redit.arch.RedditTopContract;
import com.prituladima.android.redit.model.api.RedditApi;
import com.prituladima.android.redit.model.dto.ArticleDTO;
import com.prituladima.android.redit.model.dto.ChildrenDTO;
import com.prituladima.android.redit.model.dto.ResponceDTO;
import com.prituladima.android.redit.util.Logger;
import com.prituladima.android.redit.view.RedditActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@Singleton
public class RedditPresenter extends BasePresenter<RedditTopContract.RedditTopView> implements RedditTopContract.IRedditPresenter {

    private static Logger LOGGER = new Logger(RedditPresenter.class);

    private RedditTopContract.RedditTopView redditTopView;
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
        if(subscription != null && subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    @Override
    public void syncAndUpdateView() {
        redditApi.getPage(25, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .map(new Func1<ResponceDTO, List<ChildrenDTO>>() {
                    @Override
                    public List<ChildrenDTO> call(ResponceDTO responceDTO) {
                        return responceDTO.data().children();
                    }
                })

                .map(new Func1<List<ChildrenDTO>, List<ArticleDTO>>() {
                    @Override
                    public List<ArticleDTO> call(List<ChildrenDTO> children) {
                        List<ArticleDTO> list = new ArrayList<>();
                        for(ChildrenDTO current:children)
                            list.add(current.data());

                        return list;
                    }
                })

                .subscribe(new Action1<List<ArticleDTO>>() {
                    @Override
                    public void call(List<ArticleDTO> list) {
                        LOGGER.error(list.toString());
                        getMvpView().onUpdateData(list);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LOGGER.error(throwable.toString());
                    }
                });
    }

    @Override
    public void syncAndSuggestToUpdateView() {

    }

    @Override
    public void getAndUpadateView() {

    }
}