package com.example.home.android_labs.Presenters;

import android.app.Activity;

import com.example.home.android_labs.ApplicationEx;

public class BasePresenter {
    protected ApplicationEx getApplicationInstance(Activity activity){
        ApplicationEx mApplication = (ApplicationEx) activity.getApplication();
        return mApplication;
    }
}

