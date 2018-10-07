package com.story.hacker.modules.news.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.story.hacker.R;
import com.story.hacker.base.BaseActivity;
import com.story.hacker.databinding.ActivityStoryBinding;
import com.story.hacker.modules.news.view.adapter.StoryAdapter;
import com.story.hacker.modules.news.viewmodel.StoryViewModel;
import com.story.hacker.util.Connectivity;
import com.story.hacker.util.Constants;

import java.util.ArrayList;


public class StoryActivity extends BaseActivity {

    private ActivityStoryBinding binding;
    private StoryViewModel viewModel;
    private StoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_story);
        viewModel = ViewModelProviders.of(this).get(StoryViewModel.class);
        getSupportActionBar().setTitle(Constants.STORY_TITLE);
        showProgress();
        initLoad();
        initAdapter();
        initObserver();
        initListener();
    }

    private void initListener() {
        binding.swipeToRefresh.setOnRefreshListener(() -> {
            initLoad();
        });
    }

    private void initAdapter() {
        adapter = new StoryAdapter();
        binding.recycler.setAdapter(adapter);
        adapter.submitList(null);
    }

    /**
     * Initialise live data observers
     */
    private void initObserver() {
        viewModel.getStories().observe(this, stories -> {
            hideProgress();
            if (adapter != null) {
                adapter.submitList(stories);
                binding.swipeToRefresh.setRefreshing(false);
            }
            if (stories.size() == 0)
                hideShowEmptyList(true);
            else
                hideShowEmptyList(false);
        });

        viewModel.getNetworkErrors().observe(this, s -> {
            hideProgress();
            Snackbar.make(binding.getRoot(), s, 500).show();
            binding.swipeToRefresh.setRefreshing(false);
        });

        adapter.getUrlLiveData().observe(this, url -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });
    }

    /**Load initial value
     */
    private void initLoad() {
        if (Connectivity.isConnected(this)) {
            viewModel.getTopStories();
        } else {
            Snackbar.make(binding.getRoot(), getString(R.string.offline_mode), 1000).show();
            viewModel.getStoryList(new ArrayList<>());
        }
    }

    /**
     * Method to show / hide story list and show message
     * @param status
     */
    public void hideShowEmptyList(boolean status){
        if(status){
            //Hide if list is empty
            binding.recycler.setVisibility(View.GONE);
            binding.emptyMessage.setVisibility(View.VISIBLE);
        }else{
            binding.recycler.setVisibility(View.VISIBLE);
            binding.emptyMessage.setVisibility(View.GONE);
        }
    }
}
