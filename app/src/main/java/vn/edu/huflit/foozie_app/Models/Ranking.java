package vn.edu.huflit.foozie_app.Models;

import com.google.gson.annotations.SerializedName;

public class Ranking extends BaseDTO {
    @SerializedName("code")
    public String code;
    @SerializedName("name")
    public String name;
    @SerializedName("min_exp")
    public String min_exp;
    @SerializedName("icon")
    public String icon;
}
