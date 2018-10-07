package com.story.hacker.util;

import android.app.Activity;
import android.util.Log;

import com.story.hacker.BuildConfig;

/**
 * Class to handle logs only for debug mode
 */
public class Logger {

    public static void d(Activity activity, String value) {
        if (BuildConfig.DEBUG)
            Log.d(activity.getClass().getName(), value);
    }

    public static void e(Activity activity, String value) {
        if (BuildConfig.DEBUG)
            Log.e(activity.getClass().getName(), value);
    }

    public static void i(Activity activity, String value) {
        if (BuildConfig.DEBUG)
            Log.i(activity.getClass().getName(), value);
    }

    public static void w(Activity activity, String value) {
        if (BuildConfig.DEBUG)
            Log.w(activity.getClass().getName(), value);
    }

    public static void v(Activity activity, String value) {
        if (BuildConfig.DEBUG)
            Log.v(activity.getClass().getName(), value);
    }
}
