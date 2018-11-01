package ua.lpnu.yana.apilab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApplicationEx applicationEx = (ApplicationEx) getApplication();
        applicationEx.getPokemonAPI().getData().enqueue(new Adapter());
    }
}
