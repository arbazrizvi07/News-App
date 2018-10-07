package com.story.hacker.base;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.story.hacker.util.CustomLoader;

/**
 * Base Activity extended by all Activities
 */
public abstract class BaseActivity extends AppCompatActivity {

    private CustomLoader customLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customLoader = new CustomLoader(this);
    }

    /**
     * Show Progress Dialog
     */
    public void showProgress() {
        if (!customLoader.isShowing())
            customLoader.show();
    }

    /**
     * Hide Progress Dialog
     */
    public void hideProgress() {

        if (customLoader.isShowing())
            customLoader.dismiss();
    }
}
