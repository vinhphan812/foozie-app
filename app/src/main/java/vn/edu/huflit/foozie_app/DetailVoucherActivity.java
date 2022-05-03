package vn.edu.huflit.foozie_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import vn.edu.huflit.foozie_app.Models.Voucher;
import vn.edu.huflit.foozie_app.fragments.homepage.VoucherFragment;

public class DetailVoucherActivity extends AppCompatActivity {
    TextView name, date, code, used, description;
    Button btnUse;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_voucher);
        bindWidget();
        callAPI();
        setUpWidgetListener();
    }

    private void setUpWidgetListener() {
        btnUse.setOnClickListener(v -> {

        });
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(DetailVoucherActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void callAPI() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        Voucher item = (Voucher) bundle.get("item");
        name.setText(item.name);
        date.setText(item.valid_date.toString());
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