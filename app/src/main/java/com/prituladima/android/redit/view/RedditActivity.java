package com.prituladima.android.redit.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prituladima.android.redit.R;
import com.prituladima.android.redit.RedditApplication;
import com.prituladima.android.redit.arch.RedditTopContract;
import com.prituladima.android.redit.databinding.ActivityMainBinding;
import com.prituladima.android.redit.model.dto.ArticleDTO;
import com.prituladima.android.redit.presenter.RedditPresenter;
import com.prituladima.android.redit.util.Logger;
import com.prituladima.android.redit.view.adapter.RedditAdapter;

import java.util.List;

import javax.inject.Inject;


public class RedditActivity extends AppCompatActivity implements RedditTopContract.RedditTopView {

    private static Logger LOGGER = new Logger(RedditActivity.class);

    @Inject
    RedditPresenter redditPresenter;
    RedditAdapter listAdapter;
    LinearLayoutManager linearLayoutManager;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RedditApplication.getInjector().inject(this);

        listAdapter = new RedditAdapter(this);
        binding.recyclerView.setAdapter(listAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (binding.swipeContainer.isRefreshing()) return;
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    binding.swipeContainer.setRefreshing(true);
                    redditPresenter.getRedditTop(false);
                }
            }
        });

        binding.swipeContainer.setOnRefreshListener(() -> redditPresenter.getRedditTop(true));
    }

    @Override
    protected void onStart() {
        super.onStart();
        redditPresenter.attachView(this);
        redditPresenter.getRedditTop(true);
        binding.swipeContainer.setRefreshing(true);
    }

    @Override
    protected void onStop() {
        redditPresenter.detachView();
        super.onStop();
        binding = null;
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
        binding.swipeContainer.setRefreshing(false);
    }
}