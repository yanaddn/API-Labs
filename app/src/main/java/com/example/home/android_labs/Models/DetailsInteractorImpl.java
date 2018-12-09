package com.example.home.android_labs.Models;

import com.example.home.android_labs.Entity.Card;
import com.example.home.android_labs.SharedPreferencesRepository;

public class DetailsInteractorImpl implements DetailsInteractor {

    SharedPreferencesRepository preferences;

    public DetailsInteractorImpl(SharedPreferencesRepository preferences) {
        this.preferences = preferences;
    }

    public void doFavourite(Card card, DetailsInteractor.OnFinishedListener onFinishedListener) {
        if (!preferences.contains(card.getId())) {
            preferences.add(card);
            onFinishedListener.onAdd();
        } else {
            preferences.remove(card);
            onFinishedListener.onRemove();
        }
    }

    public void isFavourite(Card card, DetailsInteractor.OnFinishedListener onFinishedListener) {
        if (preferences.contains(card.getId())) {
            onFinishedListener.favouriteResult(true);
        } else {
            onFinishedListener.favouriteResult(false);
        }
    }

}

