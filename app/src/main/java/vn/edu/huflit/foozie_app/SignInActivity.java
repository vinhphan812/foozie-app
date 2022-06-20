package vn.edu.huflit.foozie_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import vn.edu.huflit.foozie_app.Utils.Utilities;

public class SignInActivity extends AppCompatActivity {
    private TextInputLayout Username, Password;
    Button btnSignIn;
    private TextView btnSignUp;
    private SharedPreferences preferences;
    private static final String PREFS_NAME = "prefsFile";

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
                Utilities.alert(v, "Vui lòng nhập đầy đủ thông tin!", Utilities.AlertType.Info);
                return;
            }
            try {
                Utilities.api.Login(username, password);
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                Utilities.alert(getWindow().getDecorView().findViewById(android.R.id.content),"Sai mật khẩu", Utilities.AlertType.Error);
            }
        });
        //Sign up
        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    private void bindWidget() {
        Username = (TextInputLayout) findViewById(R.id.edt_user_name_signIn);
        Password = (TextInputLayout) findViewById(R.id.edt_password_signIn);
        btnSignIn = (Button) findViewById(R.id.btn_signIn);
        btnSignUp = (TextView) findViewById(R.id.btn_sign_up_signIn);
    }
}