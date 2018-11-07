package ua.lpnu.yana.apilab;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface PokemonAPI {

    @Headers("Content-Type: application/json")
    @GET("cards")
    Call<Feed> getData();
}
