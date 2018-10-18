package ua.lpnu.yana.apilab.model.cards;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ability {

    @SerializedName("name")
    @Expose
    private Ability name;

    @SerializedName("text")
    @Expose
    private String text;

    public Ability getName() {
        return name;
    }

    public void setName(Ability name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Ability{" +
                "name=" + name +
                ", text='" + text + '\'' +
                '}';
    }
}
