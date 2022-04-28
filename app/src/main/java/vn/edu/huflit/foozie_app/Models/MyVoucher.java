package vn.edu.huflit.foozie_app.Models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyVoucher {
    @SerializedName("SHIPPING")
    public List<Voucher> shipping;
    @SerializedName("USING")
    public List<Voucher> using;

    @NonNull
    @Override
    public String toString() {
        return shipping.toString() + " " + using.toString();
    }
}
