package vn.edu.huflit.foozie_app.Models;

import android.location.Address;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Order extends BaseDTO {
    @SerializedName("total_foods")
    public int total_foods;
    @SerializedName("order_date")
    public Date order_date;
    @SerializedName("voucher_ship")
    public String voucher_ship;
    @SerializedName("voucher_using")
    public String voucher_using;
    @SerializedName("delivery")
    public String delivery;
    @SerializedName("user")
    public String user;
    @SerializedName("branch")
    public String branch;
    @SerializedName("status")
    public String status;
    @SerializedName("note")
    public String note;
    @SerializedName("shipping_fee")
    public int shipping_fee;
    @SerializedName("total")
    public int total;
}
