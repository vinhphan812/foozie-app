package vn.edu.huflit.foozie_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class SignUpActivity extends AppCompatActivity {
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