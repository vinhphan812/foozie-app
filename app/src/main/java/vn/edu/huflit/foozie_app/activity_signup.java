package vn.edu.huflit.foozie_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class activity_signup extends AppCompatActivity {
    Toolbar toolbarSignUp;
    NavController navControllerSignUp;
    AppBarConfiguration appBarConfigurationSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        toolbarSignUp = findViewById(R.id.toolbar_sign_up_account);
        navControllerSignUp = Navigation.findNavController(this, R.id.nav_sign_up);
        appBarConfigurationSignUp = new AppBarConfiguration.Builder(
                R.id.name_birthday_Fragment, R.id.phone_address_Fragment, R.id.usernme_password_Fragment
        ).build();

        NavigationUI.setupWithNavController(toolbarSignUp, navControllerSignUp);
    }
}