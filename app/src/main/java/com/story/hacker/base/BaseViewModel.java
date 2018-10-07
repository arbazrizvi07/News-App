package com.story.hacker.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;

/**
 * Base ViewModel With Android Databinding
 */
public class BaseViewModel extends ViewModel implements Observable {
    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}
