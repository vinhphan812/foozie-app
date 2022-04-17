package vn.edu.huflit.foozie_app.Models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Food extends BaseDTO {
    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("price")
    public int price;
    @SerializedName("type")
    public List<FoodType> type;
    @SerializedName("thumbnail")
    public String thumbnail;

    @NonNull
    @Override
    public String toString() {
        return String.format("%s - %s - %s - %s - %s", id, name, description, price, type.toString() + "");
    }
}
