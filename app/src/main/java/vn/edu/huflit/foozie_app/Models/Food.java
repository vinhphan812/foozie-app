package vn.edu.huflit.foozie_app.Models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class  Food extends BaseDTO implements Serializable {
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
    @SerializedName("quantity")
    public int quantity;

    @NonNull
    @Override
    public String toString() {
        return String.format("%s - %s - %s - %s - %s", id, name, description, price, type.toString() + "");
    }
}
