package vn.edu.huflit.foozie_app.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Notification extends BaseDTO {
    @SerializedName("title")
    public String title;
    @SerializedName("body")
    public String body;
    @SerializedName("href")
    public String href;
    @SerializedName("recipient")
    public User recipient;
    @SerializedName("is_seen")
    public Boolean is_seen;
    @SerializedName("date")
    public Date date;
}
