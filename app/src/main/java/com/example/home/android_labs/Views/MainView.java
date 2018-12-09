package com.example.home.android_labs.Views;

import com.example.home.android_labs.Entity.Card;

import java.util.List;

public interface MainView {
    void setDataToRecyclerView(List<Card> hitArrayList);
    void refreshData(List<Card> cards);
    void onResponseFailure(Throwable throwable);
}
