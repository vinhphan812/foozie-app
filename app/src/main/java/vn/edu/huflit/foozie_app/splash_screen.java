package vn.edu.huflit.foozie_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import vn.edu.huflit.foozie_app.Utils.Utilities;

public class splash_screen extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    Animation logoAnim;
    ImageView logo;

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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Thread LoadDataThread = new Thread(() -> {
                    try {
                        // get from resource String
                        final String API_URL = getResources().getString(R.string.api_url);
                        // init app
                        Utilities.init(API_URL);

                    } catch (Exception e) {
                        Log.d("ERROR_THREAD", e.getMessage());
                    } finally {
                        Intent intent = new Intent(splash_screen.this, activity_signin.class);
                        startActivity(intent);
                        finish();
                    }
                });
                LoadDataThread.start();
            }
        }, SPLASH_SCREEN);
        // start thread loading

    }
}