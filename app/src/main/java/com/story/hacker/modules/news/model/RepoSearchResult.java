package com.story.hacker.modules.news.model;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.story.hacker.modules.news.model.Story;

public class RepoSearchResult {

    LiveData<PagedList<Story>> data;
    LiveData<String> networkErrors;

    public RepoSearchResult(LiveData<PagedList<Story>> data, LiveData<String> networkErrors) {
        this.data = data;
        this.networkErrors = networkErrors;
    }

    public LiveData<PagedList<Story>> getData() {
        return data;
    }

    public LiveData<String> getNetworkErrors() {
        return networkErrors;
    }
}
