package com.prituladima.android.redit.arch;

import com.prituladima.android.redit.model.dto.ArticleDTO;

import java.util.List;

public interface RedditTopContract {

    interface RedditTopView extends IView {

        void onNoDataAvailable();

        void onUpdateData(List<ArticleDTO> list);

        void onAddData(List<ArticleDTO> list);

        void onStopLoading();

    }

    interface IRedditPresenter {

        void getRedditTop(boolean refresh);

    }

}