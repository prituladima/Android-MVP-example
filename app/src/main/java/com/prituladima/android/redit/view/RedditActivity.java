package com.prituladima.android.redit.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.prituladima.android.redit.R;
import com.prituladima.android.redit.RedditApplication;
import com.prituladima.android.redit.arch.RedditTopContract;
import com.prituladima.android.redit.model.dto.ArticleDTO;
import com.prituladima.android.redit.presenter.RedditPresenter;
import com.prituladima.android.redit.util.Logger;
import com.prituladima.android.redit.view.adapter.ListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RedditActivity extends AppCompatActivity implements RedditTopContract.RedditTopView {

    private static Logger LOGGER = new Logger(RedditActivity.class);

    @Inject
    RedditPresenter redditPresenter;
    ListAdapter listAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RedditApplication.getInjector().inject(this);
        ButterKnife.bind(this);
        listAdapter = new ListAdapter(this);
        mRecyclerView.setAdapter(listAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSwipeRefreshLayout.setOnRefreshListener(() -> redditPresenter.syncAndUpdateView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        redditPresenter.attachView(this);
        redditPresenter.syncAndUpdateView();
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    protected void onStop() {
        redditPresenter.detachView();
        super.onStop();
    }

    @Override
    public void onUpdateData(List<ArticleDTO> list) {
        listAdapter.setData(list);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onSuggestToUpdateDate() {

    }

    @Override
    public void onServerError() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onNoInternetError() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}