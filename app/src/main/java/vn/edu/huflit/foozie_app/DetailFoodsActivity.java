package vn.edu.huflit.foozie_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.huflit.foozie_app.API.ImageAPI;
import vn.edu.huflit.foozie_app.Models.Food;

public class DetailFoodsActivity extends AppCompatActivity {
    TextView name, price, description, code;
    Button btnOrder;
    ImageView btnBack, imgFoodDetail;

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

        });
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(DetailFoodsActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void callAPI() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        Food foodsItem = (Food) bundle.get("foodsItem");
        name.setText(foodsItem.name);
        price.setText(foodsItem.price + " " + "VNĐ");
        description.setText(foodsItem.description);
        code.setText(foodsItem.type.toString());
        ImageAPI.getCorner(foodsItem.thumbnail, imgFoodDetail);
    }

    private void bindWidget() {
        name = findViewById(R.id.tv_name_food_detail);
        price = findViewById(R.id.tv_price_food_detail);
        description = findViewById(R.id.tv_description_food_detail);
        btnBack = (ImageView) findViewById(R.id.btn_back_detail);
        btnOrder = findViewById(R.id.btn_order);
        imgFoodDetail = findViewById(R.id.img_food_detail);
        code = findViewById(R.id.tv_code_food_detail);
    }
}