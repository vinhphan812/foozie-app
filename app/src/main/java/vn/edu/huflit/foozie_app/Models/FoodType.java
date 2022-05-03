package vn.edu.huflit.foozie_app.Models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FoodType extends BaseDTO implements Serializable {
    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("code")
    public String code;

    @NonNull
    @Override
    public String toString() {
        return String.format("%s - %s", name, code);
    }
}
