package ua.lpnu.yana.apilab;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import ua.lpnu.yana.apilab.model.Feed;

public interface PokemonAPI {

    String BASE_URL = "https://api.pokemontcg.io/v1/";

    @Headers("Content-Type: application/json")
    @GET("cards")
    Call<Feed> getData();
}
