package com.story.hacker.network;

import com.story.hacker.modules.news.model.Story;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Interface to keep all API calls
 */
public interface APIInterface {

    @GET
    Call<Story> getStory(@Url String url);

    @GET("topstories.json")
    Call<List<Integer>> getTopStories();
}
