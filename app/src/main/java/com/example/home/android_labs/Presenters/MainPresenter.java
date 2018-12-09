package com.example.home.android_labs.Presenters;

import android.app.Activity;

public interface MainPresenter {
    void requestDataFromServer(Activity activity);
    void updateDataFromServer(Activity activity);
    void goToFavourites(Activity activity);
}
