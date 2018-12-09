package com.example.home.android_labs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.home.android_labs.Fragments.ListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

        @BindView(R.id.frame_container)
        FrameLayout frameLayout;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);
            ApplicationEx mApplication = (ApplicationEx) getApplicationContext();
            FragmentHandler fragmentHandler = new FragmentHandler(this);
            mApplication.setFragmentHandler(fragmentHandler);
            mApplication.getFragmentHandler().setFragment(new ListFragment());
        }
}
