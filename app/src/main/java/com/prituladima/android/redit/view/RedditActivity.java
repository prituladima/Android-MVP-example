package com.prituladima.android.redit.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.prituladima.android.redit.R;
import com.prituladima.android.redit.RedditApplication;
import com.prituladima.android.redit.arch.RedditTopContract;
import com.prituladima.android.redit.model.dto.ArticleDTO;
import com.prituladima.android.redit.presenter.RedditPresenter;
import com.prituladima.android.redit.util.Logger;
import com.prituladima.android.redit.view.adapter.RedditAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RedditActivity extends AppCompatActivity implements RedditTopContract.RedditTopView {

    private static Logger LOGGER = new Logger(RedditActivity.class);

    @Inject
    RedditPresenter redditPresenter;
    RedditAdapter listAdapter;
    LinearLayoutManager linearLayoutManager;

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
        listAdapter = new RedditAdapter(this);
        mRecyclerView.setAdapter(listAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (mSwipeRefreshLayout.isRefreshing()) return;
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    redditPresenter.syncAndUpdateView(false);
                }
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(() -> redditPresenter.syncAndUpdateView(true));
    }

    @Override
    protected void onStart() {
        super.onStart();
        redditPresenter.attachView(this);
        redditPresenter.syncAndUpdateView(true);
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    protected void onStop() {
        redditPresenter.detachView();
        super.onStop();
    }

    @Override
    public void onNoDataAvailable() {
        // TODO: 1/3/18 empty view
        Toast.makeText(this, R.string.no_data_available, Toast.LENGTH_LONG).show();
        onStopLoading();
    }

    @Override
    public void onUpdateData(List<ArticleDTO> list) {
        listAdapter.setData(list);
        onStopLoading();
    }

    @Override
    public void onAddData(List<ArticleDTO> list) {
        listAdapter.addData(list);
        onStopLoading();
    }

    @Override
    public void onStopLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}