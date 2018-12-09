package com.example.home.android_labs.Retrofit;

import com.example.home.android_labs.Entity.Feed;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonAPI {
    @GET("/v1/cards")
    Call<Feed> showData();
}
