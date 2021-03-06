package vn.edu.huflit.foozie_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.huflit.foozie_app.Adapters.CartAdapter;
import vn.edu.huflit.foozie_app.Models.Food;
import vn.edu.huflit.foozie_app.Utils.Callback;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class CartActivity extends AppCompatActivity {
    ImageView btnBack;
    Button btnConfirm;
    RecyclerView rvCart;
    List<Food> foodCart;
    CartAdapter cartAdapter;
    TextView tvTotalProduct, tvTotalPrice, tvTotalProductItem;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        bindWidget();
        setUpWidgetListener();
        callAPI();
    }

    private void callAPI() {
        try {
            foodCart = Utilities.api.getCart();
            cartAdapter = new CartAdapter(foodCart, new Callback() {
                @Override
                public void call() {
                    renderTotal();
                }
            });
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rvCart.setLayoutManager(linearLayoutManager);
            rvCart.setAdapter(cartAdapter);
            renderTotal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpWidgetListener() {
        btnBack.setOnClickListener(v -> {
            renderTotal();
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            startActivity(intent);
        });
        btnConfirm.setOnClickListener(v -> {
            if (cartAdapter.mCart.isEmpty()) {
                Utilities.alert(v, "H??y th??m m??n ??n v??o gi??? h??ng", Utilities.AlertType.Error);
                return;
            } else {
                renderTotal();
                Intent intent = new Intent(CartActivity.this, CreateOrderActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void bindWidget() {
        btnBack = (ImageView) findViewById(R.id.btn_back_cart);
        btnConfirm = (Button) findViewById(R.id.btn_confirm);
        rvCart = (RecyclerView) findViewById(R.id.rv_cart);
        btnBack = (ImageView) findViewById(R.id.btn_back_cart);
        tvTotalProduct = (TextView) findViewById(R.id.tv_total_product);
        tvTotalPrice = (TextView) findViewById(R.id.tv_total_price);
        tvTotalProductItem = (TextView) findViewById(R.id.tv_total_product_item);
    }


    public void renderTotal() {
        int total = 0;
        int totalProductItem = 0;
        for (Food item : cartAdapter.mCart) {
            total += item.price * item.quantity;
            totalProductItem += item.quantity;
        }
        tvTotalProduct.setText(cartAdapter.mCart.size() + "");
        tvTotalProductItem.setText(totalProductItem + "");
        tvTotalPrice.setText(Utilities.moneyFormat(total) + " VND");
        bundle.putString("totalItem", totalProductItem + "");
        bundle.putInt("total", total);
    }
}