package vn.edu.huflit.foozie_app.Utils;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.DecimalFormat;
import java.util.List;

import vn.edu.huflit.foozie_app.API.API;
import vn.edu.huflit.foozie_app.API.ImageAPI;
import vn.edu.huflit.foozie_app.Models.Branch;

public class Utilities {
    public static List<Branch> branches;

    public enum AlertType {
        Error, Info, Success
    }

    static public String FCM;

    static public SharedPreferences store;
    static public FirebaseMessaging fmessage = FirebaseMessaging.getInstance();

    static public API api = new API();

    static public void init(String apiURL, SharedPreferences store) {
        API.HOST = apiURL;
        ImageAPI.HOST = apiURL;
        Utilities.store = store;
        String cookie = Utilities.store.getString("cookie", "");
        api.init(cookie);

    }

    static public void alert(View view, String message) {
        alert(view, message, AlertType.Info);
    }

    static public void alert(View view, String message, AlertType type) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setBackgroundTint(getColorType(type)).show();
    }

    static private int getColorType(AlertType Type) {
        switch (Type) {
            case Error:
                return Color.parseColor("#dc3545");
            case Info:
                return Color.parseColor("#0d6efd");
            case Success:
                return Color.parseColor("#198754");
            default:
                return 0;
        }
    }

    static public String getFCMToken() {
        Task<String> task = fmessage.getToken();

        while (!task.isComplete()) {
        }
        FCM = task.getResult();
        return FCM;
    }

    static public double distanceCalculate(LatLng latLng1, LatLng latLng2) {
        double R = 6371; // radius Earth

        double Phi1 = latLng1.latitude * Math.PI / 180;
        double Phi2 = latLng2.latitude * Math.PI / 180;

        double deltaPhi = (latLng2.latitude - latLng1.latitude) * (Math.PI / 180);
        double deltaLambda = (latLng2.longitude - latLng1.longitude) * (Math.PI / 180);

        double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2) + Math.cos(Phi1) * Math.cos(Phi2) * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
    static public String moneyFormat(int price){
        DecimalFormat moneyFormat = new DecimalFormat("###,###,###");
        return moneyFormat.format(price);
    }
}
