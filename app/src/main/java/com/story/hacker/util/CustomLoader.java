package com.story.hacker.util;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Window;

import com.story.hacker.R;

public class CustomLoader extends Dialog {

    public CustomLoader(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_loader);
        setCancelable(false);
    }

}
