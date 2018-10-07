package com.story.hacker.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.story.hacker.modules.news.model.Story;
import com.story.hacker.util.Constants;

@Database(entities = {Story.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class StoryDatabase extends RoomDatabase {

    private static volatile StoryDatabase INSTANCE;

    public abstract StoryDao storyDao();


    /**
     * Method
     * @param context
     * @return
     */
    public static StoryDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StoryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StoryDatabase.class, Constants.DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
