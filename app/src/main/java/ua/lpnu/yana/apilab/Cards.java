package ua.lpnu.yana.apilab;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cards {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("imageUrlHiRes")
    @Expose
    private String imageUrlHiRes;

    @SerializedName("nationalPokedexNumber")
    @Expose
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

    public String getImageUrlHiRes() {
        return imageUrlHiRes;
    }

    public void setImageUrlHiRes(String imageURL) {
        this.imageUrlHiRes = imageURL;
    }

    public String getNationalPokedexNumber() {
        return nationalPokedexNumber;
    }

    public void setNationalPokedexNumber(String nationalPokedexNumber) {
        this.nationalPokedexNumber = nationalPokedexNumber;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", imageURL='" + imageUrlHiRes + '\'' +
                ", number='" + nationalPokedexNumber + '\'' +
                '}';
    }
}
