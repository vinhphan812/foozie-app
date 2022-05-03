package vn.edu.huflit.foozie_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import vn.edu.huflit.foozie_app.R;

public class verifyAccountActivity extends AppCompatActivity {
    Toolbar toolbarVerify;
    NavController navControllerVerify;
    AppBarConfiguration appBarConfigurationVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_account);
        toolbarVerify = findViewById(R.id.toolbar_verify_account);
        navControllerVerify = Navigation.findNavController(this, R.id.nav_verify);
        appBarConfigurationVerify = new AppBarConfiguration.Builder(
                R.id.verifyEmailFragment, R.id.changePasswordFragment
        ).build();

        NavigationUI.setupWithNavController(toolbarVerify, navControllerVerify);
    }
}