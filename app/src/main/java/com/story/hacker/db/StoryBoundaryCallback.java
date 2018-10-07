package com.story.hacker.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.story.hacker.modules.news.model.Story;
import com.story.hacker.network.APIInterface;
import com.story.hacker.network.Errors;
import com.story.hacker.network.ResponseListener;
import com.story.hacker.network.RetrofitRequest;
import com.story.hacker.util.Constants;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;

public class StoryBoundaryCallback extends PagedList.BoundaryCallback<Story> {

    private final APIInterface apiService;
    private final LocalCacheManager cacheManager;
    private List<Integer> topStoriesId;
    ArrayList<Story> storyArrayList ;
    private int currentPosition = 0;
    int count = 0;

    public MutableLiveData<String> networkError; // LiveData of network errors.

    public StoryBoundaryCallback(List<Integer> topStoriesId, APIInterface apiService, LocalCacheManager cacheManager) {
        this.apiService = apiService;
        this.cacheManager = cacheManager;
        this.topStoriesId = topStoriesId;
        this.networkError = new MutableLiveData<>();
    }

    @Override
    public void onZeroItemsLoaded() {
        requestLoad();
    }

    @Override
    public void onItemAtEndLoaded(@NonNull Story itemAtEnd) {
        requestLoad();

    }

    /**
     * Method to collect 10 result from server as api return only one result at a time
     */
    private void requestLoad() {
        storyArrayList = new ArrayList<>();

        if (topStoriesId == null || topStoriesId.size() == 0 ||  currentPosition >= Constants.MAX_STORIES)
            return;

        for (int i = currentPosition; i < currentPosition + Constants.MAX_STORE_SIZE; i++) {
            requestAndSaveData(topStoriesId.get(i).toString());
        }

        currentPosition += Constants.MAX_STORE_SIZE;

    }

    public LiveData<String> getNetworkError() {
        return networkError;
    }


    private void requestAndSaveData(String id) {
        Call<Story> call = apiService.getStory("item/" + id + ".json");

        new RetrofitRequest<>(call, new ResponseListener<Story>() {
            @Override
            public void onResponse(Story story, Headers headers) {
                storyArrayList.add(story);
                count++;
                if (count == Constants.MAX_STORE_SIZE) {
                    count = 0;
                    cacheManager.insertList(storyArrayList);
                }
            }

            @Override
            public void onError(int status, Errors errors) {
                networkError.postValue(errors.getError().getMessage());
            }

            @Override
            public void onFailure(Throwable throwable) {
                networkError.postValue(throwable.getMessage());
            }
        }).enqueue();

    }
}
