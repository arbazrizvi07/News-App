package com.story.hacker.util;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class BindingUtils {

    @BindingAdapter({"url"})
    public static void loadImage(ImageView view, String url) {
        if (null != url) {
            Uri uri = Uri.parse(url);
            view.setImageURI(uri);
        }
    }

    @BindingAdapter({"refreshing"})
    public static void setRefreshing(SwipeRefreshLayout view, boolean isRefreshing) {
        view.setRefreshing(isRefreshing);
    }

}
