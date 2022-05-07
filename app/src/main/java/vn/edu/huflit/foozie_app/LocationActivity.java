package vn.edu.huflit.foozie_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class LocationActivity extends AppCompatActivity {
    private TextInputLayout location;
    Button btnAcceptLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        location = (TextInputLayout) findViewById(R.id.edt_location);
        btnAcceptLocation = (Button) findViewById(R.id.btn_accept_location);
        btnAcceptLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Location = location.getEditText().getText().toString();

            }
        });
    }
}