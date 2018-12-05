package ua.lpnu.yana.apilab;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListDetailsFragment extends Fragment {

    public static final String FAVOURITE = "Favorite";
    private static final String DETAILS = "details";
    private boolean isImageFitToScreen;
    private SharedPreferences sharedPreferences;
    private Card cards;

    @BindView(R.id.image_details)
    protected ImageView imageDetails;
    @BindView(R.id.fav)
    protected ImageView favourite;
    @BindView(R.id.title)
    protected TextView title;
    @BindView(R.id.subreddit)
    protected TextView subreddit;
    private Bundle bundle;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details, container, false);
        ButterKnife.bind(this, view);
        bundle = this.getArguments();
        showCards(getCard());
        checkFavorite();
        return view;
    }

    public Card getCard() {
        return bundle == null ? null : new Gson()
                .fromJson(bundle.getString(DETAILS), Card.class);
    }

    private void showCards(Card cards) {
        Picasso.get().load(cards.getImageUrl()).into(imageDetails);
        title.setText(cards.getId());
        subreddit.setText(cards.getName());
        sharedPreferences = getActivity().getSharedPreferences(FAVOURITE, Context.MODE_PRIVATE);
    }

    @OnClick(R.id.image_details)
    void fullScreenImage() {
        if (isImageFitToScreen) {
            isImageFitToScreen = false;
            imageDetails.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT));
            imageDetails.setAdjustViewBounds(true);
        } else {
            isImageFitToScreen = true;
            imageDetails.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT));
            imageDetails.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }

    @OnClick(R.id.fav)
    void setFavorite() {
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        if (checkFavorite()) {
            prefEditor.remove(cards.getNationalPokedexNumber());
            prefEditor.apply();
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(cards);
            prefEditor.putString(cards.getNationalPokedexNumber(), json);
            prefEditor.apply();
        }
        checkFavorite();
    }

    boolean checkFavorite() {
        if (!sharedPreferences.contains(cards.getNationalPokedexNumber())) {
            favourite.setImageResource(R.drawable.ic_fav_border);
            return false;
        } else {
            favourite.setImageResource(R.drawable.ic_fav_black);
            return true;
        }
    }
}
