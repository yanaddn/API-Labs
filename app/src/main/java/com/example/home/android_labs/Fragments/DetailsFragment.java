package com.example.home.android_labs.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.home.android_labs.Entity.Card;
import com.example.home.android_labs.Presenters.DetailsPresenter;
import com.example.home.android_labs.Presenters.DetailsPresenterImpl;
import com.example.home.android_labs.R;
import com.example.home.android_labs.Views.DetailsView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.home.android_labs.ApplicationEx.HEIGHT;

public class DetailsFragment extends Fragment implements DetailsView {

    private boolean isImageFitToScreen;
    private DetailsPresenter presenter;

    @BindView(R.id.image_details)
    ImageView imageView;
    @BindView(R.id.user_details)
    TextView user;
    @BindView(R.id.tags_details)
    TextView tags;
    @BindView(R.id.type_details)
    TextView type;
    @BindView(R.id.fav)
    ImageView fav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_details, container, false);
        ButterKnife.bind(this, view);
        presenter = new DetailsPresenterImpl(this);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlImage();
            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.checkFavourite(getActivity());
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getData(getActivity());
    }

    @Override
    public void setItems(Card cards) {
        Picasso.get().load(cards.getImageUrl()).into(imageView);
        user.setText(cards.getName());
        tags.setText(cards.getId());
        type.setText(cards.getNationalPokedexNumber());
    }

    @Override
    public void addToFavourite() {
        fav.setImageResource(R.drawable.ic_favorite_black_24dp);
    }

    @Override
    public void removeFromFavourite() {
        fav.setImageResource(R.drawable.ic_favorite_border_black_24dp);
    }

    @Override
    public void markFavourite(boolean favourite) {
        if (favourite) {
            fav.setImageResource(R.drawable.ic_favorite_black_24dp);
        }
    }

    private void controlImage() {
        if (isImageFitToScreen) {
            isImageFitToScreen = false;
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, HEIGHT));
        } else {
            isImageFitToScreen = true;
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams
                    .MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }
}
