package vn.edu.huflit.foozie_app.Models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class DiscountDTO {
    @SerializedName("price")
    public int price;
    @SerializedName("shipping_fee")
    public int shipping_fee;

    @NonNull
    @Override
    public String toString() {
        return "price: " + price + ", fee: " + shipping_fee;
    }
}
