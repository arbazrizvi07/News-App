package com.story.hacker.db;

import android.arch.paging.DataSource;

import com.story.hacker.modules.news.model.Story;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class LocalCacheManager {

    private StoryDao storyDao;
    private Executor executor;

    public LocalCacheManager(StoryDao storyDao, Executor executor) {
        this.storyDao = storyDao;
        this.executor = executor;
    }

    public void insert(Story story) {
        executor.execute(() -> storyDao.insert(story));
    }

    public void insertList(ArrayList<Story> story) {
        executor.execute(() -> storyDao.insert(story));
    }


    public DataSource.Factory<Integer, Story> getStories() {
        return storyDao.getAllStories();
    }
}
