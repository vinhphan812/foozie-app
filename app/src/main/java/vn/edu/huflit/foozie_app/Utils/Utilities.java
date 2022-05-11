package vn.edu.huflit.foozie_app.Utils;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;

import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.FirebaseMessaging;

import vn.edu.huflit.foozie_app.API.API;
import vn.edu.huflit.foozie_app.API.ImageAPI;

public class Utilities {
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

}
