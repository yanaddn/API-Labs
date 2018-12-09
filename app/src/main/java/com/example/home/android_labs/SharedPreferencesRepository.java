package com.example.home.android_labs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.home.android_labs.Entity.Card;
import com.google.gson.Gson;

import java.util.Map;

public class SharedPreferencesRepository {
    public static final String FAVOURITES = "favourites";

    private final SharedPreferences mPrefs;

    public SharedPreferencesRepository(Context context) {
        mPrefs = context.getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
    }

    public void add(Card card){
        mPrefs.edit().putString(card.getId(), new Gson().toJson(card)).commit();
    }

    public void remove(Card hit) {
        mPrefs.edit().remove(hit.getId()).commit();
    }

    public boolean contains (String name) {
        if (mPrefs.contains(name)){
            return true;
        } else {
            return false;
        }
    }

    public Map<String, ?> getAll(){
        return mPrefs.getAll();
    }
}

