package com.example.home.android_labs.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home.android_labs.Adapters.PokemonAdapter;
import com.example.home.android_labs.Entity.Card;
import com.example.home.android_labs.Presenters.MainPresenter;
import com.example.home.android_labs.Presenters.MainPresenterImpl;
import com.example.home.android_labs.R;
import com.example.home.android_labs.Views.MainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment implements MainView {
    private PokemonAdapter mAdapter;
    private MainPresenter presenter;

    @BindView(R.id.lvMain)
    protected RecyclerView mRecycleView;
    @BindView(R.id.list_empty)
    protected TextView noData;
    @BindView(R.id.swipe_container)
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.move)
    protected Button mMoveToFavourites;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        ButterKnife.bind(this, view);
        initializeRecyclerView();
        presenter = new MainPresenterImpl(this);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.updateDataFromServer(getActivity());
            }
        });

        mMoveToFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goToFavourites(getActivity());
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.requestDataFromServer(getActivity());
    }

    private void initializeRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecycleView.setLayoutManager(layoutManager);
    }

    @Override
    public void setDataToRecyclerView(List<Card> hitArrayList) {
        mAdapter = new PokemonAdapter(getActivity(), hitArrayList);
        mRecycleView.setAdapter(mAdapter);
    }

    @Override
    public void refreshData(List<Card> cards) {
        mAdapter.clear();
        mAdapter.addAll(cards);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(), R.string.unsuccessful_response
                + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}

