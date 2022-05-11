package vn.edu.huflit.foozie_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.huflit.foozie_app.API.API;
import vn.edu.huflit.foozie_app.API.ImageAPI;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class testActivity extends AppCompatActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        image = findViewById(R.id.testImageView);

        SharedPreferences sharedPref = this.getSharedPreferences(String.valueOf(R.string.localStoreName), Context.MODE_PRIVATE);

        Utilities.init(getResources().getString(R.string.api_url), sharedPref);

        API api = Utilities.api;

        try {
            //region User
//        Log.d("SignUp", api.SignUp("phanvinh637", "12345678", "Phan", "Vinh", "phanvinh637@gmail.com", "0335454323"));

            Log.d("Login", api.Login("vinhphan812", "12345678"));
            Log.d("getMe", api.getMe().toString());
            Log.d("MyVouchers", api.getMyVouchers() + "");

            Log.d("Logout", api.Logout());
            //endregion

            //region Public
            Log.d("Foods", api.getFoods(null, null).size() + "");
            Log.d("FoodType", api.getFoodTypes().size() + "");
            Log.d("Branches", api.getBranches().size() + "");
            Log.d("Food_62263546c44f38a0f56e7360", api.getFood("62263546c44f38a0f56e7360").toString());
            Log.d("GetCart", api.getCart().size() + "");
            Log.d("addCart", api.addCart("62263546c44f38a0f56e7360"));
            Log.d("Voucher", api.getVoucherPublic().size() + "");

            ImageAPI.get("/images/rankings/diamond.png", image);
            //endregion
        } catch (Exception e) {
            Log.d("Exception", e.getMessage());
        }

    }
}