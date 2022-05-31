package vn.edu.huflit.foozie_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vn.edu.huflit.foozie_app.Models.Branch;
import vn.edu.huflit.foozie_app.Models.User;
import vn.edu.huflit.foozie_app.Models.Voucher;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class CreateOrderActivity extends AppCompatActivity implements OnMapReadyCallback {
    TextView nameUser, phoneUser, addressUser, addressBranch, totalItems, totalPrices, totalShip, voucherUser;
    User user;
    SearchView svLocation;
    GoogleMap map;
    SupportMapFragment mapFragment;
    ImageView btnBack;
    TextInputLayout edtAddNote;
    Button btnPay;
    List<Voucher> voucher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        nameUser = findViewById(R.id.tv_name_user);
        phoneUser = findViewById(R.id.tv_phone_user);
        svLocation = findViewById(R.id.search_location);
        btnBack = (ImageView) findViewById(R.id.btn_back_order);
        addressBranch = findViewById(R.id.tv_address_branch);
        addressUser = findViewById(R.id.tv_address_user);
        totalItems = findViewById(R.id.tv_total_item);
        totalPrices = findViewById(R.id.tv_total_price_order);
        totalShip = findViewById(R.id.tv_total_ship_price);
        voucherUser = findViewById(R.id.tv_voucher_user);
        edtAddNote = findViewById(R.id.edt_add_note);
        btnPay = findViewById(R.id.btn_pay);

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(CreateOrderActivity.this, CartActivity.class);
            startActivity(intent);
        });

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);

        try {
            user = Utilities.api.getMe();
        } catch (Exception e) {
            e.printStackTrace();
        }
        nameUser.setText(user.first_name + " " + user.last_name);
        phoneUser.setText(user.phone);

        //location
        svLocation.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = svLocation.getQuery().toString();
                List<Address> addressList = null;
                if (location != null || location.equals("")) {
                    Geocoder geocoder = new Geocoder(CreateOrderActivity.this);
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
                    Collections.sort(Utilities.branches, Comparator.comparing(x -> x.distance));
                    for (int i = 0; i < Utilities.branches.size(); i++) {
                        map.addMarker(new MarkerOptions()
                                .position(Utilities.branches.get(i).latLng)
                                .title(Utilities.branches.get(i).name)
                                .icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.store), 100, 100, false)))
                        );
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(Utilities.branches.get(0).latLng, 14));
                        map.addPolyline(new PolylineOptions().add(latLng1, Utilities.branches.get(0).latLng));
                    }
                    addressUser.setText(location);
                    addressBranch.setText(Utilities.branches.get(0).name);

                    try {
                        totalShip.setText(Utilities.api.distanceCalculateShippingFee(Utilities.branches.get(0).distance) + "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mapFragment.getMapAsync(this);
        Bundle b = getIntent().getExtras();
        String items = b.getString("totalItem");
        String prices = b.getString("total");
        totalItems.setText(items + "");
        totalPrices.setText(prices + "");

        btnPay.setOnClickListener(v -> {
            String branchId = Utilities.branches.get(0).id;
            String note = edtAddNote.getEditText().getText().toString();
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
    }
}