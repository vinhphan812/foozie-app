package vn.edu.huflit.foozie_app;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.huflit.foozie_app.API.API;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class testActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        API api = new API();

        try {
            //region User
//        Log.d("SignUp", api.SignUp("phanvinh637", "12345678", "Phan", "Vinh", "phanvinh637@gmail.com", "0335454323"));

            Log.d("Login", api.Login("vinhphan812", "12345678"));
            Log.d("getMe", api.getMe().toString());
            Log.d("MyVouchers", api.getMyVouchers().size() + "");

            Log.d("Logout", api.Logout());
            //endregion

            //region Public
            Log.d("Foods", api.getFoods().size() + "");
            Log.d("FoodType", api.getFoodTypes().size() + "");
            Log.d("Branches", api.getBranches().size() + "");
            Log.d("Food_62263546c44f38a0f56e7360", api.getFood("62263546c44f38a0f56e7360").toString());
            Log.d("GetCart", api.getCart().size() + "");
            Log.d("addCart", api.addCart("62263546c44f38a0f56e7360"));
            Log.d("Voucher", api.getVoucherPublic().size() + "");
            //endregion
        } catch (Exception e) {
            Log.d("Exception", e.getMessage());
        }

    }
}