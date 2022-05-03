package vn.edu.huflit.foozie_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import vn.edu.huflit.foozie_app.Models.User;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class activity_signin extends AppCompatActivity {
    private CheckBox btnRememberAccount;
    private TextInputLayout Username, Password;
    Button btnSignIn;
    private TextView btnSignUp, btnFotGetPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        bindWidget();
        setUpWidgetListener();
    }

    private void setUpWidgetListener() {
        //Sign in
        btnSignIn.setOnClickListener(v -> {
            String username = Username.getEditText().getText().toString();
            String password = Password.getEditText().getText().toString();
            if (username.isEmpty() || password.isEmpty()) {
                Snackbar.make(v, "Vui lòng nhập đầy đủ thông tin!", Snackbar.LENGTH_LONG).show();
                return;
            }
            if (password.length() < 8) {
                Snackbar.make(v, "Mật khẩu phải trên 8 ký tự", Snackbar.LENGTH_LONG).show();
                return;
            }
            try {
                Utilities.api.Login(username, password);
                Intent intent = new Intent(activity_signin.this, MainActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                Utilities.alert(v, e.getMessage());
            }
        });
        //Sign up
        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(activity_signin.this, activity_signup.class);
            startActivity(intent);
        });
        //Forget password
        btnFotGetPass.setOnClickListener(v -> {
            Intent intent = new Intent(activity_signin.this, verifyAccountActivity.class);
            startActivity(intent);
        });
    }

    private void bindWidget() {
        Username = (TextInputLayout) findViewById(R.id.edt_user_name_signIn);
        Password = (TextInputLayout) findViewById(R.id.edt_password_signIn);
        btnSignIn = (Button) findViewById(R.id.btn_signIn);
        btnSignUp = (TextView) findViewById(R.id.btn_sign_up_signIn);
        btnFotGetPass = (TextView) findViewById(R.id.btn_fotGetPass_signIn);
        btnRememberAccount = (CheckBox) findViewById(R.id.chk_SignIn);
    }
}