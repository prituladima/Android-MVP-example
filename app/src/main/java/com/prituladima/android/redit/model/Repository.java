package com.prituladima.android.redit.model;

import com.prituladima.android.redit.RedditApplication;
import com.prituladima.android.redit.model.api.RedditApi;
import com.prituladima.android.redit.model.db.HawkLocalStorage;
import com.prituladima.android.redit.model.dto.ArticleDTO;
import com.prituladima.android.redit.model.dto.ResponseDTO;
import com.prituladima.android.redit.util.Logger;
import com.prituladima.android.redit.util.Mappers;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Repository {
    private static Logger LOGGER = new Logger(Repository.class);
    public static final int DEFAULT_PAGE_SIZE = 25;

    @Inject
    RedditApi redditApi;
    @Inject
    HawkLocalStorage localStorage;

    private List<String> hashPointers = new ArrayList<>();

    {
        hashPointers.add("");
    }

    private int nextPage = 0;

    @Inject
    public Repository() {
        RedditApplication.getInjector().inject(this);
    }

    public Observable<List<ArticleDTO>> getRedditTop() {
        return redditApi.getPage(DEFAULT_PAGE_SIZE, hashPointers.get(nextPage))

                .map(debug -> {
                    System.out.println(debug);
                    return debug;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .map(responceDTO -> localStorage.saveTop(responceDTO, DEFAULT_PAGE_SIZE, hashPointers.get(nextPage)))
                .onErrorReturn(throwable -> {
                    System.out.println(throwable);
                    return localStorage.getTop(DEFAULT_PAGE_SIZE, hashPointers.get(nextPage));
                })
                .map(this::increaseCounter)
                .map(Mappers.getRedditMapper())
                .onErrorReturn(throwable -> {
                    LOGGER.error(throwable);
                    return new ArrayList<>();
                });
    }

    private ResponseDTO increaseCounter(ResponseDTO responseDTO) {
        hashPointers.add(responseDTO.data().after());
        nextPage++;
        return responseDTO;
    }

    public void resetCounter() {
        hashPointers.clear();
        hashPointers.add("");
        nextPage = 0;
    }

}