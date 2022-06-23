package vn.edu.huflit.foozie_app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.huflit.foozie_app.API.ImageAPI;
import vn.edu.huflit.foozie_app.Models.Food;
import vn.edu.huflit.foozie_app.R;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class ListFoodOrderAdapter extends RecyclerView.Adapter<ListFoodOrderAdapter.ViewHolderFood> {
    public List<Food> mfoodsOrder;

    public ListFoodOrderAdapter(List<Food> mfoodsOrder) {
        this.mfoodsOrder = mfoodsOrder;
    }

    @NonNull
    @Override
    public ViewHolderFood onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_order, parent, false);
        return new ListFoodOrderAdapter.ViewHolderFood(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFood holder, int position) {
        Food food = mfoodsOrder.get(position);
        holder.tvFoodNameOrder.setText(food.name);
        holder.tvFoodPriceOrder.setText(Utilities.moneyFormat(food.price) + " " + "VND");
        ImageAPI.getCorner(food.thumbnail, holder.imgFoodOrder);
        holder.edtQuantity.setText(food.quantity + " ");
    }

    @Override
    public int getItemCount() {
        return mfoodsOrder.size();
    }

    public class ViewHolderFood extends RecyclerView.ViewHolder {
        ImageView imgFoodOrder;
        TextView tvFoodNameOrder, tvFoodPriceOrder;
        EditText edtQuantity;

        public ViewHolderFood(@NonNull View itemView) {
            super(itemView);
            edtQuantity = itemView.findViewById(R.id.edtQuantity);
            imgFoodOrder = (ImageView) itemView.findViewById(R.id.img_food_cart);
            tvFoodNameOrder = (TextView) itemView.findViewById(R.id.tv_food_name_cart);
            tvFoodPriceOrder = (TextView) itemView.findViewById(R.id.tv_food_price_cart);
        }
    }
}
