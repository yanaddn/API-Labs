package com.example.home.android_labs.Models;

import com.example.home.android_labs.Entity.Card;

public interface DetailsInteractor {

    interface OnFinishedListener {
        void onAdd();
        void onRemove();
        void favouriteResult(boolean change);
    }

    void doFavourite(Card card, DetailsInteractor.OnFinishedListener onFinishedListener);
    void isFavourite(Card card, DetailsInteractor.OnFinishedListener onFinishedListener);
}
