package vn.edu.huflit.foozie_app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import vn.edu.huflit.foozie_app.Adapters.FoodAdapter;
import vn.edu.huflit.foozie_app.Adapters.ListFoodOrderAdapter;
import vn.edu.huflit.foozie_app.Models.Food;
import vn.edu.huflit.foozie_app.Models.Order;
import vn.edu.huflit.foozie_app.Models.User;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class DetailMyOrderActivity extends AppCompatActivity {
    ImageView btnBack;
    TextView tvDateOrder, tvIdOrder, tvTotalPrices, tvUserOrder, tvNoteOrder, tvPhoneUser, tvAddressUser;
    Order detailOrder;
    User user;
    RecyclerView rvFoodDetail;
    List<Food> foods;
    ListFoodOrderAdapter listFoodOrderAdapter;
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
        if (id.isEmpty()) {
            super.onBackPressed();
        }
        try {
            detailOrder = Utilities.api.getOrder(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvDateOrder.setText(dateFormat.format(detailOrder.order_date));
        tvIdOrder.setText(detailOrder.id);
        tvTotalPrices.setText(Utilities.moneyFormat(detailOrder.total) + " VND");

        if (detailOrder.note.isEmpty()) {
            tvNoteOrder.setText("Chưa có ghi chú cho đơn hàng này");
        } else {
            tvNoteOrder.setText(detailOrder.note);
        }

        foods = detailOrder.details;
        listFoodOrderAdapter = new ListFoodOrderAdapter(foods);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvFoodDetail.setLayoutManager(linearLayoutManager);
        rvFoodDetail.setAdapter(listFoodOrderAdapter);

        tvAddressUser.setText(detailOrder.delivery);
        try {
            user = Utilities.api.getMe();
            tvUserOrder.setText(user.first_name + " " + user.last_name);
            tvPhoneUser.setText(user.phone);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        rvFoodDetail = findViewById(R.id.rv_food_detail_order);
    }
}