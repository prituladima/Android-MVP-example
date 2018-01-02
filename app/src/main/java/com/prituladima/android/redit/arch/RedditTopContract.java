package com.prituladima.android.redit.arch;

import com.prituladima.android.redit.model.dto.ArticleDTO;

import java.util.List;

public interface RedditTopContract {

    interface RedditTopView extends IView {

        void onUpdateData(List<ArticleDTO> list);

        void onSuggestToUpdateDate();
        
        void onServerError();

        void onNoInternetError();

    }

    interface IRedditPresenter {

        void syncAndUpdateView();

        void syncAndSuggestToUpdateView();

        void getAndUpadateView();

    }

}