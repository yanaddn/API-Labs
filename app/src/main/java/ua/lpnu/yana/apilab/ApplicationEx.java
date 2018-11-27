package ua.lpnu.yana.apilab;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationEx extends Application {

    private static final String BASE_URL = "https://api.pokemontcg.io/v1/";

    public Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public PokemonAPI getPokemonAPI() {
        return getRetrofitInstance().create(PokemonAPI.class);
    }
}
