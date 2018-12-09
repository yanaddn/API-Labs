package com.example.home.android_labs.Models;

import com.example.home.android_labs.Entity.Card;

import java.util.List;

public interface MainInteractor {
    interface OnFinishedListener {
        void onFinishedLoad(List<Card> hitArrayList);

        void onFinishedReload(List<Card> hitArrayList);

        void onFailure(Throwable t);
    }

    void getHitArrayList(MainInteractor.OnFinishedListener onFinishedListener, boolean dataChanged);
}

