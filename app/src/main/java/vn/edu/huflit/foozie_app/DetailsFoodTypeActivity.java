package vn.edu.huflit.foozie_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.huflit.foozie_app.Models.FoodType;

public class DetailsFoodTypeActivity extends AppCompatActivity {
    RecyclerView rvFoodTypeDetail;
    TextView name;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_food_type);
        bindWidget();
        callAPI();
        setUpWidgetListener();
    }

    private void setUpWidgetListener() {
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(DetailsFoodTypeActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void callAPI() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        FoodType typeItem = (FoodType) bundle.get("typeItem");
        name.setText(typeItem.name);


    }

    private void bindWidget() {
        rvFoodTypeDetail = findViewById(R.id.rv_food_type_detail);
        name = findViewById(R.id.tv_name_food_type_detail);
        btnBack = (ImageView) findViewById(R.id.btn_back_detail);
    }
}