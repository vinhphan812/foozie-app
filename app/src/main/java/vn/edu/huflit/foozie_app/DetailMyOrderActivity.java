package vn.edu.huflit.foozie_app;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

import vn.edu.huflit.foozie_app.Models.Order;
import vn.edu.huflit.foozie_app.Models.User;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class DetailMyOrderActivity extends AppCompatActivity {
    ImageView btnBack;
    TextView tvDateOrder, tvIdOrder, tvTotalPrices, tvUserOrder, tvNoteOrder, tvPhoneUser, tvAddressUser;
    Order detailOrder;
    User user;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_my_order);
        bindWidget();
        callAPI();
        setUpWidgetListener();
    }

    private void setUpWidgetListener() {
        btnBack.setOnClickListener(v -> onBackPressed());
    }

    private void callAPI() {
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id", "");

        if (id.isEmpty())
            super.onBackPressed();

        detailOrder = null;
        try {
            detailOrder = Utilities.api.getOrder(id);
        } catch (Exception e) {
            Log.d("GET_FOOD_" + id, e.getMessage());
        }
        tvDateOrder.setText(dateFormat.format(detailOrder.order_date));
        tvIdOrder.setText(detailOrder.id);
        tvTotalPrices.setText(Utilities.moneyFormat(detailOrder.total) + " VND");
        try {
            user = Utilities.api.getMe();
            tvUserOrder.setText(user.first_name + " " + user.last_name);
            tvPhoneUser.setText(user.phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (detailOrder.note.isEmpty()) {
            tvNoteOrder.setText("Chưa có ghi chú cho đơn hàng này");
        } else {
            tvNoteOrder.setText(detailOrder.note);
        }
        tvAddressUser.setText(detailOrder.delivery);
    }

    private void bindWidget() {
        btnBack = findViewById(R.id.btn_back_my_order_detail);
        tvDateOrder = findViewById(R.id.tv_date_order_detail);
        tvIdOrder = findViewById(R.id.tv_id_order_detail);
        tvTotalPrices = findViewById(R.id.tv_total_prices_order_detail);
        tvUserOrder = findViewById(R.id.tv_user_order_detail);
        tvNoteOrder = findViewById(R.id.tv_note_order_detail);
        tvPhoneUser = findViewById(R.id.tv_phone_user_order_detail);
        tvAddressUser = findViewById(R.id.tv_address_user_order_detail);

    }
}