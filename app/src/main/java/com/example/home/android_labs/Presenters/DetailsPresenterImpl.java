package com.example.home.android_labs.Presenters;

import android.app.Activity;

import com.example.home.android_labs.Entity.Card;
import com.example.home.android_labs.Models.DetailsInteractor;
import com.example.home.android_labs.Views.DetailsView;

public class DetailsPresenterImpl extends BasePresenter
        implements DetailsPresenter, DetailsInteractor.OnFinishedListener {

    private DetailsView view;
    private Card card;

    public DetailsPresenterImpl(DetailsView view) {
        this.view = view;
    }

    public void onAdd() {
        view.addToFavourite();
    }

    public void onRemove() {
        view.removeFromFavourite();
    }

    public void checkFavourite(Activity activity) {
        getApplicationInstance(activity).getDetailsInteractor().
                doFavourite(card, this);
    }

    public void favouriteResult(boolean favourite) {
        view.markFavourite(favourite);
    }

    public void getData(Activity activity) {
        card = getApplicationInstance(activity).getFragmentHandler().getArguments();
        view.setItems(card);
        getApplicationInstance(activity).getDetailsInteractor().isFavourite(card,
                this);
    }
}
