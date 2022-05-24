package vn.edu.huflit.foozie_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vn.edu.huflit.foozie_app.Models.Branch;
import vn.edu.huflit.foozie_app.Models.Food;
import vn.edu.huflit.foozie_app.Models.User;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class OrderActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView nameUser, phoneUser;
    User user;
    SearchView svLocation;
    GoogleMap map;
    SupportMapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        nameUser = findViewById(R.id.tv_name_user);
        phoneUser = findViewById(R.id.tv_phone_user);
        svLocation = findViewById(R.id.search_location);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        try {
            user = Utilities.api.getMe();
            nameUser.setText(user.first_name + " " + user.last_name);
            phoneUser.setText(user.phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        svLocation.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = svLocation.getQuery().toString();
                List<Address> addressList = null;
                if (location != null || location.equals("")) {
                    Geocoder geocoder = new Geocoder(OrderActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address1 = addressList.get(0);
                    LatLng latLng1 = new LatLng(address1.getLatitude(), address1.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng1).title(location));
                    for (Branch branch : Utilities.branches) {
                        branch.distance = Utilities.distanceCalculate(branch.latLng, latLng1);
                    }
                    map.addMarker(new MarkerOptions()
                            .position(Utilities.branches.get(0).latLng)
                            .title(Utilities.branches.get(0).name)
                            .icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.store), 100, 100, false)))
                    );
                    Collections.sort(Utilities.branches, Comparator.comparing(x -> x.distance));
                    map.addMarker(new MarkerOptions()
                            .position(Utilities.branches.get(1).latLng)
                            .title(Utilities.branches.get(1).name)
                            .icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.store), 100, 100, false)))
                    );
                    map.addMarker(new MarkerOptions()
                            .position(Utilities.branches.get(2).latLng)
                            .title(Utilities.branches.get(2).name)
                            .icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.store), 100, 100, false)))
                    );
                    map.addMarker(new MarkerOptions()
                            .position(Utilities.branches.get(3).latLng)
                            .title(Utilities.branches.get(3).name)
                            .icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.store), 100, 100, false)))
                    );
                    map.addMarker(new MarkerOptions()
                            .position(Utilities.branches.get(4).latLng)
                            .title(Utilities.branches.get(4).name)
                            .icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.store), 100, 100, false)))
                    );
                    map.addMarker(new MarkerOptions()
                            .position(Utilities.branches.get(5).latLng)
                            .title(Utilities.branches.get(5).name)
                            .icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.store), 100, 100, false)))
                    );
                    map.addPolyline(new PolylineOptions().clickable(false).add(
                            latLng1,
                            Utilities.branches.get(0).latLng
                    ));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(Utilities.branches.get(0).latLng, 14));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
    }
}