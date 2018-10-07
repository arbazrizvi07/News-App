package com.story.hacker.db;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.story.hacker.modules.news.model.Story;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface StoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Story story);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArrayList<Story> stories);

    @Query("DELETE FROM story")
    void deleteAll();

    @Query("SELECT * from story ORDER BY score DESC")
    DataSource.Factory<Integer, Story> getAllStories();
}
