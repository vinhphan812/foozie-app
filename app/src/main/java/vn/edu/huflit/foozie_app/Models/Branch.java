package vn.edu.huflit.foozie_app.Models;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

import vn.edu.huflit.foozie_app.R;

public class Branch {
    @SerializedName("address")
    public String address;
    @SerializedName("name")
    public String name;
    @SerializedName("phone")
    public String phone;
    @SerializedName("latLng")
    public LatLng latLng;

    @NonNull
    @Override
    public String toString() {
        return String.format("%s - %f,%f", name, latLng.latitude, latLng.longitude);
    }
//            const R = 6371e3; // metres
//const φ1 = lat1 * Math.PI/180; // φ, λ in radians
//const φ2 = lat2 * Math.PI/180;
//const Δφ = (lat2-lat1) * Math.PI/180;
//const Δλ = (lon2-lon1) * Math.PI/180;
//
//const a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
//            Math.cos(φ1) * Math.cos(φ2) *
//            Math.sin(Δλ/2) * Math.sin(Δλ/2);
//const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
//
//const d = R * c;

}
