package com.example.home.android_labs.Models;

import com.example.home.android_labs.Entity.Card;

import java.util.List;

public interface FavouritesInteractor {
    interface OnFinishedListener {
        void addData(List<Card> cards);
    }
    void getHitArrayList(FavouritesInteractor.OnFinishedListener onFinishedListener);
}

