package ua.lpnu.yana.apilab;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Feed {

    @SerializedName("cards")
    @Expose
    private ArrayList<Card> cards;

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    /*
    @Override
    public String toString() {
        return "Feed{" +
                "cards=" + cards +
                '}';
    }
    */
}
