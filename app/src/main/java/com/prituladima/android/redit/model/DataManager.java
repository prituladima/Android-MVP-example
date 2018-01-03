package com.prituladima.android.redit.model;

import com.prituladima.android.redit.RedditApplication;
import com.prituladima.android.redit.model.api.RedditApi;
import com.prituladima.android.redit.model.db.HawkLocalStorage;
import com.prituladima.android.redit.model.dto.ArticleDTO;
import com.prituladima.android.redit.model.dto.ResponceDTO;
import com.prituladima.android.redit.util.Mappers;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataManager {

    public static final int DEFAULT_PAGE_SIZE = 25;

    @Inject
    RedditApi redditApi;
    @Inject
    HawkLocalStorage localStorage;

    List<String> heshes = new ArrayList<>();

    {
        heshes.add("");
    }

    int nextPage = 0;

    @Inject
    public DataManager() {
        RedditApplication.getInjector().inject(this);
    }

    public Observable<List<ArticleDTO>> sync() {
        return redditApi.getPage(DEFAULT_PAGE_SIZE, heshes.get(nextPage))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(responceDTO -> localStorage.saveTop(responceDTO, DEFAULT_PAGE_SIZE, heshes.get(nextPage)))
                .onErrorReturn(throwable -> localStorage.getTop(DEFAULT_PAGE_SIZE, heshes.get(nextPage)))
                .map(this::increaseCounter)
                .map(Mappers.getRedditMapper())
                .onErrorReturn(throwable -> new ArrayList<>());
    }

    public ResponceDTO increaseCounter(ResponceDTO responceDTO) {
        heshes.add(responceDTO.data().after());
        nextPage++;
        return responceDTO;
    }

    public void resetCounter() {
        heshes.clear();
        heshes.add("");
        nextPage = 0;
    }

}