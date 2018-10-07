package com.story.hacker.modules.news;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;

import com.story.hacker.StoryApplication;
import com.story.hacker.db.LocalCacheManager;
import com.story.hacker.db.StoryBoundaryCallback;
import com.story.hacker.db.StoryDatabase;
import com.story.hacker.modules.news.model.RepoSearchResult;
import com.story.hacker.modules.news.model.Story;
import com.story.hacker.network.Errors;
import com.story.hacker.network.ResponseListener;
import com.story.hacker.network.RetrofitClient;
import com.story.hacker.network.RetrofitRequest;

import java.util.List;
import java.util.concurrent.Executors;

import okhttp3.Headers;
import retrofit2.Call;

public class NewsRepository {
    private static final int DATABASE_PAGE_SIZE = 10;
    private LocalCacheManager localCacheManager;
    private LiveData resultResponse;
    private LiveData<String> networkErrors;

    public NewsRepository() {
        StoryDatabase database = StoryDatabase.getDatabase(StoryApplication.getInstance());
        localCacheManager = new LocalCacheManager(database.storyDao(), Executors.newSingleThreadExecutor());
    }


    /**
     * Load All Top Stories from server
     * @param successResponse
     */
    public void loadTopStory(SuccessResponse<List<Integer>> successResponse) {
        Call<List<Integer>> call = RetrofitClient.getAPIInterface().getTopStories();

        new RetrofitRequest<>(call, new ResponseListener<List<Integer>>() {
            @Override
            public void onResponse(List<Integer> response, Headers headers) {
                successResponse.onResponse(response);
            }

            @Override
            public void onError(int status, Errors errors) {
            }

            @Override
            public void onFailure(Throwable throwable) {
            }
        }).enqueue();

    }

    /**
     * Paging method for Async Pagination
     *
     * @param topStoriesId
     * @return
     */
    public RepoSearchResult getStory(List<Integer> topStoriesId) {

        DataSource.Factory<Integer, Story> dataSourceFactory = localCacheManager.getStories();
        StoryBoundaryCallback boundaryCallback = new StoryBoundaryCallback(
                topStoriesId, RetrofitClient.getAPIInterface(), localCacheManager);

        networkErrors = boundaryCallback.getNetworkError();

        // Get the paged list
        resultResponse = new LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build();

        return new RepoSearchResult(resultResponse, networkErrors);
    }


    public interface SuccessResponse<T> {
        void onResponse(T t);
    }
}
