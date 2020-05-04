package com.prituladima.android.redit.view;

import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
    RecyclerView recyclerView;

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RedditApplication.getInjector().inject(this);
        ButterKnife.bind(this);
        listAdapter = new RedditAdapter(this);
        recyclerView.setAdapter(listAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (swipeRefreshLayout.isRefreshing()) return;
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    swipeRefreshLayout.setRefreshing(true);
                    redditPresenter.getRedditTop(false);
                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(() -> redditPresenter.getRedditTop(true));
    }

    @Override
    protected void onStart() {
        super.onStart();
        redditPresenter.attachView(this);
        redditPresenter.getRedditTop(true);
        swipeRefreshLayout.setRefreshing(true);
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
        swipeRefreshLayout.setRefreshing(false);
    }
}