package vn.edu.huflit.foozie_app.Models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class User extends BaseDTO {
    @SerializedName("username")
    public String username;
    @SerializedName("email")
    public String email;
    @SerializedName("phone")
    public String phone;
    @SerializedName("first_name")
    public String first_name;
    @SerializedName("last_name")
    public String last_name;
    @SerializedName("avatar")
    public String avatar;
    @SerializedName("current_ranking")
    public Ranking ranking;
    @SerializedName("score")
    public double score;
    @SerializedName("isVerifyMail")
    public String isVerify;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("updated_at")
    public String updated_at;
    @SerializedName("myHistoryOrderCount")
    public int myHistoryOrderCount;
    @SerializedName("myVoucherCount")
    public int myVoucherCount;  
    @NonNull
    @Override
    public String toString() {
        return String.format("%s - %s %s - %s - %s %s %s", id, first_name, last_name, username, email, phone, ranking.name);
    }
}
