package com.story.hacker.modules.news.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.PagedList;

import com.story.hacker.base.BaseViewModel;
import com.story.hacker.modules.news.NewsRepository;
import com.story.hacker.modules.news.model.RepoSearchResult;
import com.story.hacker.modules.news.model.Story;

import java.util.List;

/**
 * Story View Model work as Controller for Story Screen
 *
 * Used Live Data Operators like Map, SwitchMap for handling live events
 *
 * Class is extended by ViewModel which help it to work on Portrait and Horizontal Mode
 */
public class StoryViewModel extends BaseViewModel {

    private NewsRepository repository;
    private MutableLiveData<List<Integer>> queryLiveData;
    public LiveData<PagedList<Story>> stories;
    public LiveData<String> networkErrors;

    public StoryViewModel() {
        repository = new NewsRepository();
        queryLiveData = new MutableLiveData<>();
        LiveData<RepoSearchResult> storyResponse = Transformations.map(queryLiveData, input -> repository.getStory(input));
        stories = Transformations.switchMap(storyResponse, input -> input.getData());
        networkErrors = Transformations.switchMap(storyResponse, input -> input.getNetworkErrors());
    }

    public LiveData<PagedList<Story>> getStories() {
        return stories;
    }

    public LiveData<String> getNetworkErrors() {
        return networkErrors;
    }

    public void getTopStories() {
        repository.loadTopStory(storiesList -> getStoryList(storiesList));
    }

    public void getStoryList(List<Integer> storiesList) {
        queryLiveData.postValue(storiesList);
    }
}
