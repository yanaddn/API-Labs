package ua.lpnu.yana.apilab;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private PokemonAPI pokemonAPI;
    private Call<Feed> call;
    private SwipeRefreshLayout swipeContainer;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Card> mCards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        retrofitInstance();
        showCards();
        Log.d("TAG", "ABC" + Integer.toString(mCards.size()));
        initRecyclerView();
        initRefreshLayout();
    }

    private void retrofitInstance() {
        Log.d("TAG", "init retrofit");
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.pokemontcg.io")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        pokemonAPI = retrofit.create(PokemonAPI.class);
    }


    private void initRefreshLayout() {
        swipeContainer = findViewById(R.id.swipe_layout);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrofitInstance();
                showCards();
            }
        });
    }

    private void showCards() {
        call = pokemonAPI.showData();
        call.enqueue(new Callback<Feed>() {

            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                recyclerViewAdapter.clearData();
                mCards.clear();
                Log.d("TAG", "onResponse: received information: " + response.body().toString());

                Feed example = response.body();
                if (response.body() != null) {
                    List<Card> cardList = example.getCards();
                    mCards.addAll(cardList);
                    Log.d("TAG", "showCards():" + Integer.toString(mCards.size()));
                    for (Card card : mCards){
                        Log.d("f","card"+ card.getId());
                    }
                    recyclerViewAdapter.addAll(mCards);
                    swipeContainer.setRefreshing(false);
                }
            }

            @Override
            public void onFailure (@NonNull Call < Feed > call, @NonNull Throwable t){
                Log.e("onFailure", t.getMessage());
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerViewAdapter = new RecyclerViewAdapter(mCards);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
