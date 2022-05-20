package vn.edu.huflit.foozie_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import vn.edu.huflit.foozie_app.Models.User;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class OrderActivity extends AppCompatActivity{

    TextView nameUser, phoneUser;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        nameUser = findViewById(R.id.tv_name_user);
        phoneUser = findViewById(R.id.tv_phone_user);
        try {
            user = Utilities.api.getMe();
            nameUser.setText(user.first_name + " " + user.last_name);
            phoneUser.setText(user.phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}