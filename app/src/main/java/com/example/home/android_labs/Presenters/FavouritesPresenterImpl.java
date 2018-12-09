package com.example.home.android_labs.Presenters;

import android.app.Activity;

import com.example.home.android_labs.Entity.Card;
import com.example.home.android_labs.Models.FavouritesInteractor;
import com.example.home.android_labs.Views.FavouritesView;

import java.util.List;

public class FavouritesPresenterImpl extends BasePresenter implements FavouritesPresenter,
        FavouritesInteractor.OnFinishedListener {

    FavouritesView view;

    public FavouritesPresenterImpl(FavouritesView view) {
        this.view = view;
    }

    public void addData(List<Card> cards) {
        view.setDataToRecyclerView(cards);
    }

    public void requestDataFromStorage(Activity activity) {
        getApplicationInstance(activity).getFavouritesInteractor().
                getHitArrayList(this);
    }
}

