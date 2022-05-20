package vn.edu.huflit.foozie_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

import vn.edu.huflit.foozie_app.Models.Branch;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    Animation logoAnim;
    ImageView logo;
    Boolean isHaveAccount = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        //Animation
        logoAnim = AnimationUtils.loadAnimation(this, R.anim.logo_anim);

        //Hooks
        logo = findViewById(R.id.logo);
        logo.setAnimation(logoAnim);


        SharedPreferences store = this.getSharedPreferences(String.valueOf(R.string.localStoreName), Context.MODE_PRIVATE);

        Thread LoadDataThread = new Thread(() -> {
            try {
                // get from resource String
                final String API_URL = getResources().getString(R.string.api_url);

                // init app
                Utilities.init(API_URL, store);

                Utilities.FCM = store.getString("FCM", "");

                if (Utilities.FCM.isEmpty()) {
                    Utilities.getFCMToken();
                }

                Utilities.branches = Utilities.api.getBranches();

                LatLng latlng1 = Utilities.branches.get(0).latLng;
                LatLng latlng2 = Utilities.branches.get(1).latLng;

                Log.d("Distance", Utilities.distanceCalculate(latlng1,latlng2) + "");

                isHaveAccount = Utilities.api.validAccount();

                SharedPreferences.Editor editor = store.edit();

                editor.putString("FCM", Utilities.FCM);

                editor.commit();
            } catch (Exception e) {
                Log.d("ERROR_THREAD", e.getMessage());
            } finally {


                Intent intent = new Intent(SplashActivity.this, isHaveAccount ? MainActivity.class : SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // start thread
        LoadDataThread.start();

    }
}