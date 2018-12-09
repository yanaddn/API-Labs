package com.example.home.android_labs;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.home.android_labs.Entity.Card;
import com.example.home.android_labs.Fragments.DetailsFragment;
import com.google.gson.Gson;

import static com.example.home.android_labs.ApplicationEx.DETAILS;

public class FragmentHandler {

    private final MainActivity activity;

    private Fragment currentFragment;

    public FragmentHandler(final MainActivity activity) {
        this.activity = activity;
    }

    public void setFragment(final Fragment fragment) {
        activity.getSupportFragmentManager().
                beginTransaction()
                .replace(R.id.frame_container, fragment)
                .addToBackStack(null)
                .commit();
        currentFragment = fragment;
    }

    public void goToDetails(final Card card) {
        final DetailsFragment fragment = new DetailsFragment();
        final Bundle bundle = new Bundle();
        bundle.putString(DETAILS, new Gson().toJson(card));
        fragment.setArguments(bundle);
        setFragment(fragment);
    }

    public Card getArguments() {
        if (currentFragment instanceof DetailsFragment) {
            final Bundle arguments = currentFragment.getArguments();
            if (arguments != null) {
                Card card = new Gson().fromJson(arguments.getString(DETAILS), Card.class);
                return card;
            }
        }
        return null;
    }
}

