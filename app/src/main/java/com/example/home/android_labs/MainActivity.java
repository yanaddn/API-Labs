package com.example.home.android_labs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.home.android_labs.Adapters.PokemonAdapter;
import com.example.home.android_labs.Fragments.ListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String DETAILS = "details";
    public static final String FAVOURITES = "favourites";
    private PokemonAdapter mAdapter;

    @BindView(R.id.frame_container)
    FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setFragment(new ListFragment());
    }

    public void setFragment(final Fragment fragment) {
        getSupportFragmentManager().
                beginTransaction()
                .replace(R.id.frame_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
