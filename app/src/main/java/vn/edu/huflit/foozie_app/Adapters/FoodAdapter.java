package vn.edu.huflit.foozie_app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import vn.edu.huflit.foozie_app.API.ImageAPI;
import vn.edu.huflit.foozie_app.Models.Food;
import vn.edu.huflit.foozie_app.R;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolderFood> {
    public List<Food> mfoods;
    private Listener mlistener;

    public FoodAdapter(List<Food> foods, Listener listener) {
        this.mfoods = foods;
        this.mlistener = listener;
    }

    public interface Listener {
        void onClick(Food foodsItem);
    }

    @NonNull
    @Override
    public ViewHolderFood onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodAdapter.ViewHolderFood(view);
    }

    private DecimalFormat moneyFormat = new DecimalFormat("0.00");

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFood holder, int position) {
        Food food = mfoods.get(position);
        holder.tvNameFood.setText(food.name);
        holder.tvPriceFood.setText(moneyFormat.format(food.price) + " " + "Vnđ");
        holder.tvCodeFood.setText(food.type.toString());
        ImageAPI.getCorner(food.thumbnail, holder.imgFood);
        holder.itemView.setOnClickListener(v -> mlistener.onClick((Food) food));
    }

    @Override
    public int getItemCount() {
        return mfoods.size();
    }

    public class ViewHolderFood extends RecyclerView.ViewHolder {
        ImageView imgFood;
        TextView tvNameFood, tvPriceFood, tvCodeFood;

        public ViewHolderFood(View view) {
            super(view);
            imgFood = view.findViewById(R.id.img_food);
            tvNameFood = view.findViewById(R.id.tv_name_food);
            tvPriceFood = view.findViewById(R.id.tv_price_food);
            tvCodeFood = view.findViewById(R.id.tv_code_food);
        }
    }
}
