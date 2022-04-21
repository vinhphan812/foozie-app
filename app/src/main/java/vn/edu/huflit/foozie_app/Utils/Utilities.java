package vn.edu.huflit.foozie_app.Utils;

import android.graphics.Color;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import vn.edu.huflit.foozie_app.API.API;

public class Utilities {
    static public API api = new API();

    static public void alert(View view, String message) {
        alert(view, message, "Info");
    }

    static public void alert(View view, String message, String type) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setBackgroundTint(getColorType(type)).show();
    }

    private static int getColorType(String Type) {
        switch (Type) {
            case "Error":
                return Color.parseColor("#dc3545");
            case "Info":
                return Color.parseColor("#0d6efd");
            case "Success":
                return Color.parseColor("#198754");
            default:
                return 0;

        }
    }

}
