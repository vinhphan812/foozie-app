package vn.edu.huflit.foozie_app.Models;

import android.util.SparseBooleanArray;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;


public class Voucher extends BaseDTO implements Serializable {
    @SerializedName("name")
    public String name;
    @SerializedName("icon")
    public String icon;
    @SerializedName("code")
    public String code;
    @SerializedName("description")
    public String description;
    @SerializedName("voucher_type")
    public String voucher_type;
    @SerializedName("discount_type")
    public String discount_type;
    @SerializedName("discount")
    public int discount;
    @SerializedName("max_used")
    public int max_used;
    @SerializedName("used")
    public int used;
    @SerializedName("valid_date")
    public Date valid_date;
    @SerializedName("min_price")
    public int min_price;
    @SerializedName("max_price")
    public int max_price;

    private boolean isClicked= false;

    public boolean getClicked() {
        return isClicked;
    }

    public void HandleClick() {
        isClicked = !isClicked;
    }

    public void setClicked(boolean clicked) { isClicked = clicked;}
}
