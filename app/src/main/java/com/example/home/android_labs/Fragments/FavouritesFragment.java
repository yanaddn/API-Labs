package com.example.home.android_labs.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.home.android_labs.Adapters.PokemonAdapter;
import com.example.home.android_labs.Entity.Card;
import com.example.home.android_labs.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.home.android_labs.MainActivity.FAVOURITES;

public class FavouritesFragment extends Fragment {

    private PokemonAdapter mAdapter;
    private List<Card> cards;
    private SharedPreferences mPrefs;

    @BindView(R.id.lvMain)
    RecyclerView mRecycleView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_favourites, container, false);
        ButterKnife.bind(this, view);
        cards = new ArrayList<>();
        getFavourites();
        setmAdapter(cards, mAdapter, mRecycleView, getActivity());
        return view;
    }

    private void getFavourites() {
        mPrefs = getActivity().getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
        Map<String, ?> map = mPrefs.getAll();

        for (Map.Entry<String, ?> entry : map.entrySet()) {
            Card card = new Gson().fromJson(entry.getValue().toString(), Card.class);
            cards.add(card);
        }
    }

    public void setmAdapter(List<Card> cards, PokemonAdapter adapter,
                            RecyclerView recyclerView, Activity activity) {
        adapter = new PokemonAdapter(activity, cards);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
