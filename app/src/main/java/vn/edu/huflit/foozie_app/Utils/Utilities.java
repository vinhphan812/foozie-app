package vn.edu.huflit.foozie_app.Utils;

import android.graphics.Color;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import vn.edu.huflit.foozie_app.API.*;

public class Utilities {
    public enum AlertType {
        Error, Info, Success
    }

    static public API api = new API();

    static public void init(String apiURL) {
        API.HOST = apiURL;
        ImageAPI.HOST = apiURL;
    }

    static public void alert(View view, String message) {
        alert(view, message, AlertType.Info);
    }

    static public void alert(View view, String message, AlertType type) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setBackgroundTint(getColorType(type)).show();
    }

    private static int getColorType(AlertType Type) {
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

}
