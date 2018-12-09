package com.example.home.android_labs.Fragments;

import android.app.Activity;
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
import com.example.home.android_labs.Entity.Feed;
import com.example.home.android_labs.MainActivity;
import com.example.home.android_labs.R;
import com.example.home.android_labs.Retrofit.RetroClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {
    private PokemonAdapter mAdapter;
    private boolean isChange = false;

    @BindView(R.id.lvMain)
    RecyclerView mRecycleView;
    @BindView(R.id.list_empty)
    TextView noData;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.move)
    Button mMoveToFavourites;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        ButterKnife.bind(this, view);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isChange = true;
                makeCall();
            }
        });

       mMoveToFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) v.getContext()).setFragment(new FavouritesFragment());
            }
        });

        makeCall();
        return view;
    }

    private void makeCall() {
        Call<Feed> call = RetroClient.getApiService().showData();
        call.clone().enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Toast.makeText(getActivity(), R.string.successful_response,
                        Toast.LENGTH_LONG).show();
                if (response.body() == null) {
                    noData.setText(R.string.no_data);
                } else {
                    List<Card> cards = response.body().getCards();
                    if(!isChange) {
                    setmAdapter(cards, mRecycleView, getActivity());
                    } else {
                        refreshData(cards);
                    }
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Toast.makeText(getActivity(), R.string.unsuccessful_response
                        + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setmAdapter(List<Card> cards,
                            RecyclerView recyclerView, Activity activity) {
        mAdapter = new PokemonAdapter(activity, cards);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    public void refreshData(List<Card> cards){
        mAdapter.clear();
        mAdapter.addAll(cards);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
