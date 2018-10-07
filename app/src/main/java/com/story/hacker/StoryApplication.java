package com.story.hacker;

import android.app.Application;
import android.content.Context;

import com.story.hacker.network.RetrofitClient;

public class StoryApplication extends Application {

    private static StoryApplication appInstance;

    public static StoryApplication getInstance() {
        return appInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        RetrofitClient.create();
    }

}
