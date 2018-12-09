package com.example.home.android_labs.Views;

import com.example.home.android_labs.Entity.Card;

public interface DetailsView {
    void addToFavourite();
    void removeFromFavourite();
    void markFavourite(boolean favourite);
    void setItems(Card card);
}

