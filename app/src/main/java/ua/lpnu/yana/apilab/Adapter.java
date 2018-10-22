package ua.lpnu.yana.apilab;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Adapter implements Callback <Feed> {

    private static final String TAG = "Adapter";
    private static final String BASE_URL = "https://api.pokemontcg.io/v1/";


    public void makeRequest() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonAPI pokemonAPI = retrofit.create(PokemonAPI.class);
        Call <Feed> call = pokemonAPI.getData();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Feed> call, Response<Feed> response) {
        Log.d(TAG, "onResponse: Server Response: " + response.toString());
        Log.d(TAG, "onResponse: received information: " + response.body().toString());

        ArrayList<Cards> cardsList = response.body().getCards();
        for (Cards card : cardsList) {

            Log.d(TAG, "id: " + card.getId()+ "\n" +
                    "name: " + card.getName()+ "\n" +
                    "image: " + card.getImageUrlHiRes()+ "\n" +
                    "number: " + card.getNationalPokedexNumber()+ "\n" +
                    "_____________________________\n\n");
        }
    }

    @Override
    public void onFailure(@NonNull Call <Feed> call, @NonNull Throwable t) {
        Log.e("onFailure", t.getMessage());
    }
}