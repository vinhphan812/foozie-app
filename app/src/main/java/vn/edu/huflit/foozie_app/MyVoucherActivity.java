package vn.edu.huflit.foozie_app;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.huflit.foozie_app.Adapters.VoucherAdapter;
import vn.edu.huflit.foozie_app.Models.Voucher;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class MyVoucherActivity extends AppCompatActivity implements VoucherAdapter.Listener {
    RecyclerView rvMyVoucher;
    VoucherAdapter voucherAdapter;
    List<Voucher> myVouchers;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_voucher);
        try {
            rvMyVoucher = findViewById(R.id.rv_my_voucher);
            myVouchers = Utilities.api.getMyVouchers();
            voucherAdapter = new VoucherAdapter(myVouchers, this, 1);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyVoucherActivity.this, LinearLayoutManager.VERTICAL, false);
            rvMyVoucher.setLayoutManager(linearLayoutManager);
            rvMyVoucher.setAdapter(voucherAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        btnBack = findViewById(R.id.btn_back_my_voucher);
        btnBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    @Override
    public void onClick(Voucher voucherItem) {
    }

}