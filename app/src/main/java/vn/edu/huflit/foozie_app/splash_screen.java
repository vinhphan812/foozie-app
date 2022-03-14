package vn.edu.huflit.foozie_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splash_screen extends AppCompatActivity {
    private static int SPLASH_SCREEN=5000;
    Animation logoAnim;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        //Animation
        logoAnim= AnimationUtils.loadAnimation(this,R.anim.logo_anim);

        //Hooks
        logo=findViewById(R.id.logo);
        logo.setAnimation(logoAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(splash_screen.this,intro_screen.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}