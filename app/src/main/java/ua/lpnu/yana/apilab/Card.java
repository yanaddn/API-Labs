package ua.lpnu.yana.apilab;

import com.google.gson.annotations.SerializedName;

public class Card {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("nationalPokedexNumber")
    private String nationalPokedexNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageURL) {
        this.imageUrl = imageURL;
    }

    public String getNationalPokedexNumber() {
        return nationalPokedexNumber;
    }

    public void setNationalPokedexNumber(String nationalPokedexNumber) {
        this.nationalPokedexNumber = nationalPokedexNumber;
    }
}

