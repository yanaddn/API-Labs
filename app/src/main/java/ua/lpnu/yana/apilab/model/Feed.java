package ua.lpnu.yana.apilab.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import ua.lpnu.yana.apilab.model.cards.Cards;

public class Feed {

    @SerializedName("cards")
    @Expose
    private ArrayList<Cards> cards;

    public ArrayList<Cards> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Cards> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "cards=" + cards +
                '}';
    }
}
