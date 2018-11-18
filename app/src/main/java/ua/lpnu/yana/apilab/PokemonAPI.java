package ua.lpnu.yana.apilab;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface PokemonAPI {

    @GET("https://api.pokemontcg.io/v1/cards")
    Call<Feed>showData();
}
