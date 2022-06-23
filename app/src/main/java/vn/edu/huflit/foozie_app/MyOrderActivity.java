package vn.edu.huflit.foozie_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.huflit.foozie_app.Adapters.OrderAdapter;
import vn.edu.huflit.foozie_app.Models.Order;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class MyOrderActivity extends AppCompatActivity implements OrderAdapter.Listener {
    RecyclerView rvMyOrder;
    OrderAdapter orderAdapter;
    List<Order> myOrder;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        rvMyOrder = findViewById(R.id.rv_my_order);
        try {
            myOrder = Utilities.api.getHistoryOrders();
            orderAdapter = new OrderAdapter(myOrder, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyOrderActivity.this, LinearLayoutManager.VERTICAL, false);
            rvMyOrder.setLayoutManager(linearLayoutManager);
            rvMyOrder.setAdapter(orderAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        btnBack = findViewById(R.id.btn_back_my_order);
        btnBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    @Override
    public void onClick(Order order) {
        Intent intent = new Intent(MyOrderActivity.this, DetailMyOrderActivity.class);
        intent.putExtra("id", order.id);
        startActivity(intent);
    }
}