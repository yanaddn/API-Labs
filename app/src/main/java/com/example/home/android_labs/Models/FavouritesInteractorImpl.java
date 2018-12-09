package com.example.home.android_labs.Models;

import com.example.home.android_labs.Entity.Card;
import com.example.home.android_labs.SharedPreferencesRepository;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FavouritesInteractorImpl implements FavouritesInteractor {

    SharedPreferencesRepository preferences;

    public FavouritesInteractorImpl(SharedPreferencesRepository preferences) {
        this.preferences = preferences;
    }

    public void getHitArrayList(FavouritesInteractor.OnFinishedListener onFinishedListener) {
        List<Card> cards = new ArrayList<>();
        Map<String, ?> map = preferences.getAll();

        for (Map.Entry<String, ?> entry : map.entrySet()) {
            Card card = new Gson().fromJson(entry.getValue().toString(), Card.class);
            cards.add(card);
        }
        onFinishedListener.addData(cards);
    }
}

