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

import java.text.DecimalFormat;
import java.util.List;

import vn.edu.huflit.foozie_app.API.ImageAPI;
import vn.edu.huflit.foozie_app.Adapters.FoodAdapter;
import vn.edu.huflit.foozie_app.Models.Food;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class DetailFoodsActivity extends AppCompatActivity implements FoodAdapter.Listener {
    TextView name, price, description, code;
    Button btnOrder;
    ImageView btnBack, imgFoodDetail;
    RecyclerView rvFoodDetail;
    FoodAdapter foodAdapter;
    List<Food> foods;
    Food foodsItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_foods);
        bindWidget();
        callAPI();
        setUpWidgetListener();
    }

    private void setUpWidgetListener() {

        btnOrder.setOnClickListener(v -> {
            Bundle bundle = getIntent().getExtras();
            String id = bundle.getString("id", "");
            try {
                Utilities.api.addCart(id);
            } catch (Exception e) {
                Utilities.alert(v, e.getMessage(), Utilities.AlertType.Error);
            }
            Intent i = new Intent(DetailFoodsActivity.this, MainActivity.class);
            startActivity(i);
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(DetailFoodsActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private DecimalFormat moneyFormat = new DecimalFormat("0.00");

    private void callAPI() {
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id", "");

        if (id.isEmpty())
            super.onBackPressed();

        foodsItem = null;
        try {
            foodsItem = Utilities.api.getFood(id);
        } catch (Exception e) {
            Log.d("GET_FOOD_" + id, e.getMessage());
        }
        name.setText(foodsItem.name);
        price.setText(moneyFormat.format(foodsItem.price) + " VND");
        if (foodsItem.description.isEmpty()) {
            description.setText("Chưa có nội dung để hiển thị");
        } else {
            description.setText(foodsItem.description);
        }
        code.setText(foodsItem.type.get(0).name);

        ImageAPI.getCorner(foodsItem.thumbnail, imgFoodDetail);
        try {
            foods = Utilities.api.getFoods(foodsItem.type.get(0).id, null);
            foodAdapter = new FoodAdapter(foods, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            rvFoodDetail.setLayoutManager(linearLayoutManager);
            rvFoodDetail.setAdapter(foodAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindWidget() {
        name = findViewById(R.id.tv_name_food_detail);
        price = findViewById(R.id.tv_price_food_detail);
        description = findViewById(R.id.tv_description_food_detail);
        btnBack = (ImageView) findViewById(R.id.btn_back_detail);
        btnOrder = findViewById(R.id.btn_order);
        imgFoodDetail = findViewById(R.id.img_food_detail);
        code = findViewById(R.id.tv_code_food_detail);
        rvFoodDetail = findViewById(R.id.rv_food_detail);
    }

    @Override
    public void onClick(Food foodsItem) {
        Intent intent = new Intent(this, DetailFoodsActivity.class);
        intent.putExtra("id", foodsItem.id);
        startActivity(intent);
    }
}