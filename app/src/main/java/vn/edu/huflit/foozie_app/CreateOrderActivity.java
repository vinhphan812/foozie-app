package vn.edu.huflit.foozie_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import vn.edu.huflit.foozie_app.Adapters.VoucherAdapter;
import vn.edu.huflit.foozie_app.Models.Branch;
import vn.edu.huflit.foozie_app.Models.DiscountDTO;
import vn.edu.huflit.foozie_app.Models.User;
import vn.edu.huflit.foozie_app.Models.Voucher;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class CreateOrderActivity extends AppCompatActivity implements OnMapReadyCallback, VoucherAdapter.Listener {
    TextView nameUser, phoneUser, addressUser, addressBranch, totalItems, totalPrices, totalShip, totalOrder;
    User user;
    SearchView svLocation;
    GoogleMap map;
    SupportMapFragment mapFragment;
    ImageView btnBack;
    TextInputLayout edtAddNote;
    Button btnPay;
    List<Voucher> myVoucher;
    VoucherAdapter voucherAdapter;
    RecyclerView rvMyVoucher;
    int totalPricesAfterUsingShippingFee, shippingFee, prices;
    DiscountDTO priceAfterUsingVoucher;
    String idVoucherUsing, location, delivery;
    Address address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        bindWidget();
        setUpWidgetListener();
        callAPI();
        mapSetUp();
    }

    private void mapSetUp() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        //location
        svLocation.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                location = svLocation.getQuery().toString();
                List<Address> addressList = null;
                try {
                    if (location != null || location.equals("")) {
                        Geocoder geocoder = new Geocoder(CreateOrderActivity.this);
                        try {
                            addressList = geocoder.getFromLocationName(location, 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        address = addressList.get(0);
                        delivery = address.getAddressLine(0);
                        LatLng latLng1 = new LatLng(address.getLatitude(), address.getLongitude());
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
                        addressUser.setText(delivery);
                        addressBranch.setText(Utilities.branches.get(0).name);
                        try {
                            shippingFee = Utilities.api.distanceCalculateShippingFee(Utilities.branches.get(0).distance);
                            totalShip.setText(Utilities.moneyFormat(shippingFee) + " VND");
                            totalPricesAfterUsingShippingFee = Utilities.api.distanceCalculateShippingFee(Utilities.branches.get(0).distance);
                            totalOrder.setText(Utilities.moneyFormat(totalPricesAfterUsingShippingFee + prices) + " VND");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    Utilities.alert(getWindow().getDecorView().getRootView(), "Địa chỉ không tồn tại!", Utilities.AlertType.Error);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mapFragment.getMapAsync(this);

    }

    private void callAPI() {
        Bundle b = getIntent().getExtras();
        String items = b.getString("totalItem");
        prices = b.getInt("total");
        totalItems.setText(items + "");
        totalPrices.setText(Utilities.moneyFormat(prices) + " VND");
        totalOrder.setText(Utilities.moneyFormat(prices) + " VND");
        addressUser.setText("Trống");
        addressBranch.setText("Trống");
        try {
            user = Utilities.api.getMe();
        } catch (Exception e) {
            e.printStackTrace();
        }
        nameUser.setText(user.first_name + " " + user.last_name);
        phoneUser.setText(user.phone);
        //
        try {
            myVoucher = Utilities.api.getMyVouchers();
            voucherAdapter = new VoucherAdapter(myVoucher, this, 0);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CreateOrderActivity.this, LinearLayoutManager.HORIZONTAL, false);
            rvMyVoucher.setLayoutManager(linearLayoutManager);
            rvMyVoucher.setAdapter(voucherAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpWidgetListener() {
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(CreateOrderActivity.this, CartActivity.class);
            startActivity(intent);
        });

        btnPay.setOnClickListener(v -> {
            if (location == null) {
                Utilities.alert(v, "Vui lòng nhập địa chỉ!", Utilities.AlertType.Error);
            } else {
                String branchId = Utilities.branches.get(0).id;
                String note = edtAddNote.getEditText().getText().toString();
                Utilities.api.createOrders(branchId, note, idVoucherUsing, Utilities.branches.get(0).distance, delivery);
                Intent intent = new Intent(CreateOrderActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void bindWidget() {
        nameUser = findViewById(R.id.tv_name_user);
        phoneUser = findViewById(R.id.tv_phone_user);
        svLocation = findViewById(R.id.search_location);
        btnBack = (ImageView) findViewById(R.id.btn_back_order);
        addressBranch = findViewById(R.id.tv_address_branch);
        addressUser = findViewById(R.id.tv_address_user);
        totalItems = findViewById(R.id.tv_total_item);
        totalPrices = findViewById(R.id.tv_total_price_order);
        totalShip = findViewById(R.id.tv_total_ship_price);
        edtAddNote = findViewById(R.id.edt_add_note);
        btnPay = findViewById(R.id.btn_pay);
        rvMyVoucher = findViewById(R.id.rv_get_my_voucher);
        totalOrder = findViewById(R.id.tv_total_order);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
    }

    @Override
    public void onClick(Voucher voucherItem) {
        if (location == null) {
            voucherItem.HandleClick();
            Utilities.alert(getWindow().getDecorView().findViewById(android.R.id.content), "Vui lòng nhập địa chỉ!", Utilities.AlertType.Error);
        } else {
            if (voucherItem.getClicked()) {
                idVoucherUsing = voucherItem.id;
                try {
                    priceAfterUsingVoucher = Utilities.api.checkVoucher(idVoucherUsing, prices, shippingFee);
                    totalOrder.setText(Utilities.moneyFormat(priceAfterUsingVoucher.price + priceAfterUsingVoucher.shipping_fee) + " VND");
                } catch (Exception e) {
                    voucherItem.HandleClick();
                    Utilities.alert(getWindow().getDecorView().findViewById(android.R.id.content), "Không đủ điều kiện", Utilities.AlertType.Error);
                }
            } else {
                idVoucherUsing = null;
                totalOrder.setText(Utilities.moneyFormat(totalPricesAfterUsingShippingFee + prices) + " VND");
            }
        }

    }
}