package com.example.home.android_labs.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    private static final String ROOT_URL = "https://api.pokemontcg.io";

    private RetroClient(){};

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static PokemonAPI getApiService() {
        return getRetrofitInstance().create(PokemonAPI.class);
    }
}
