package com.example.home.android_labs.Presenters;

import android.app.Activity;

import com.example.home.android_labs.Entity.Card;
import com.example.home.android_labs.Fragments.FavouritesFragment;
import com.example.home.android_labs.Models.MainInteractor;
import com.example.home.android_labs.Views.MainView;

import java.util.List;

public class MainPresenterImpl extends BasePresenter implements MainPresenter,
        MainInteractor.OnFinishedListener {

    private MainView view;

    public MainPresenterImpl(MainView mainView) {
        this.view = mainView;
    }

    @Override
    public void requestDataFromServer(Activity activity) {
        getApplicationInstance(activity).getMainInteractor()
                .getHitArrayList(this, false);
    }

    @Override
    public void updateDataFromServer(Activity activity) {
        getApplicationInstance(activity).getMainInteractor()
                .getHitArrayList(this, true);
    }

    @Override
    public void onFinishedLoad(List<Card> hitArrayList) {
        if (view != null) {
            view.setDataToRecyclerView(hitArrayList);
        }
    }

    @Override
    public void onFinishedReload(List<Card> hitArrayList) {
        if (view != null) {
            view.refreshData(hitArrayList);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (view != null) {
            view.onResponseFailure(t);
        }
    }

    @Override
    public void goToFavourites(Activity activity){
        getApplicationInstance(activity).getFragmentHandler().setFragment(new FavouritesFragment());
    }
}