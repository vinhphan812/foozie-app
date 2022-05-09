package vn.edu.huflit.foozie_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

import vn.edu.huflit.foozie_app.Models.Voucher;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class DetailVoucherActivity extends AppCompatActivity {
    TextView name, date, code, used, description;
    Button btnUse;
    ImageView btnBack;
    Voucher item;
    String id;

    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm YYYY-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_voucher);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        item = (Voucher) bundle.get("item");
        id = bundle.getString("id");
        bindWidget();
        setUpWidgetListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        callAPI();
    }

    private void setUpWidgetListener() {
        btnUse.setOnClickListener(v -> {
            try {
                Utilities.api.takeVoucher(id);
                super.onBackPressed();
            } catch (Exception e) {
                Utilities.alert(getWindow().getDecorView().getRootView(), e.getMessage(), Utilities.AlertType.Error);
            }
        });
        btnBack.setOnClickListener(v -> {
            super.onBackPressed();
        });
    }

    private void callAPI() {
        name.setText(item.name);
        date.setText(dateFormat.format(item.valid_date));
        code.setText(item.code);
        used.setText(item.max_used + "");
        description.setText(item.description);
    }

    private void bindWidget() {
        name = findViewById(R.id.tv_name_voucher_item);
        date = findViewById(R.id.tv_date_voucher_item);
        code = findViewById(R.id.tv_code_voucher_item);
        used = findViewById(R.id.tv_used_voucher_item);
        description = findViewById(R.id.tv_description_voucher_item);
        btnUse = (Button) findViewById(R.id.btn_use_voucher);
        btnBack = (ImageView) findViewById(R.id.btn_back);
    }
}