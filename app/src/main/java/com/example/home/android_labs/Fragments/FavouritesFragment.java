package com.example.home.android_labs.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.home.android_labs.Adapters.PokemonAdapter;
import com.example.home.android_labs.Entity.Card;
import com.example.home.android_labs.Presenters.FavouritesPresenter;
import com.example.home.android_labs.Presenters.FavouritesPresenterImpl;
import com.example.home.android_labs.R;
import com.example.home.android_labs.Views.FavouritesView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouritesFragment extends Fragment implements FavouritesView {

    private PokemonAdapter mAdapter;
    private FavouritesPresenter presenter;

    @BindView(R.id.lvMain)
    protected RecyclerView mRecycleView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_favourites, container, false);
        ButterKnife.bind(this, view);
        presenter = new FavouritesPresenterImpl(this);
        initializeRecyclerView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.requestDataFromStorage(getActivity());
    }

    private void initializeRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecycleView.setLayoutManager(layoutManager);
    }

    @Override
    public void setDataToRecyclerView(List<Card> cards) {
        mAdapter = new PokemonAdapter(getActivity(), cards);
        mRecycleView.setAdapter(mAdapter);
    }
}

