package com.example.home.android_labs.Models;

import com.example.home.android_labs.Entity.Feed;
import com.example.home.android_labs.Retrofit.PokemonAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInteractorImpl implements MainInteractor {

    private PokemonAPI pokemonAPI;

    public MainInteractorImpl(PokemonAPI pokemonAPI){
        this.pokemonAPI = pokemonAPI;
    }

    @Override
    public void getHitArrayList(final OnFinishedListener onFinishedListener,
                                final boolean dataChanged) {
        Call<Feed> call = pokemonAPI.showData();
        call.clone().enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if(!dataChanged) {
                    onFinishedListener.onFinishedLoad(response.body().getCards());
                }else {
                    onFinishedListener.onFinishedReload(response.body().getCards());
                }
            }
            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }



}

