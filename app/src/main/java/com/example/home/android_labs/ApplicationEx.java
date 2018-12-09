package com.example.home.android_labs;

import android.app.Application;

import com.example.home.android_labs.Models.DetailsInteractor;
import com.example.home.android_labs.Models.DetailsInteractorImpl;
import com.example.home.android_labs.Models.FavouritesInteractor;
import com.example.home.android_labs.Models.FavouritesInteractorImpl;
import com.example.home.android_labs.Models.MainInteractor;
import com.example.home.android_labs.Models.MainInteractorImpl;
import com.example.home.android_labs.Retrofit.PokemonAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationEx extends Application {
    public static final String DETAILS = "details";
    public static final int HEIGHT = 1150;
    private static final String ROOT_URL = "https://api.pokemontcg.io";

    private DetailsInteractor detailsInteractor;
    private FavouritesInteractor favouritesInteractor;
    private MainInteractor mainInteractor;
    private SharedPreferencesRepository repository;
    private FragmentHandler fragmentHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        setupItems();
    }

    private PokemonAPI createApiClient() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(PokemonAPI.class);
    }

    private void setupItems() {
        final PokemonAPI apiClient = createApiClient();
        mainInteractor = new MainInteractorImpl(apiClient);
        repository = new SharedPreferencesRepository(getApplicationContext());
        favouritesInteractor = new FavouritesInteractorImpl(repository);
        detailsInteractor = new DetailsInteractorImpl(repository);
    }

    public DetailsInteractor getDetailsInteractor() {
        return detailsInteractor;
    }

    public FavouritesInteractor getFavouritesInteractor() {
        return favouritesInteractor;
    }

    public MainInteractor getMainInteractor() {
        return mainInteractor;
    }

    public SharedPreferencesRepository getPreferences() {
        return repository;
    }

    public FragmentHandler getFragmentHandler() {
        return fragmentHandler;
    }

    public void setFragmentHandler(FragmentHandler fragmentHandler) {
        this.fragmentHandler = fragmentHandler;
    }
}
