package vn.edu.huflit.foozie_app.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.DoubleStream;

import vn.edu.huflit.foozie_app.API.ImageAPI;
import vn.edu.huflit.foozie_app.Models.Food;
import vn.edu.huflit.foozie_app.R;
import vn.edu.huflit.foozie_app.Utils.Callback;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolderCart> {
    public List<Food> mCart;
    Callback callback;

    public CartAdapter(List<Food> mfoods, Callback callback) {
        this.mCart = mfoods;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartAdapter.ViewHolderCart(view);
    }

    private DecimalFormat moneyFormat = new DecimalFormat("0.00");

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderCart holder, int position) {
        Food food = mCart.get(position);
        holder.tvFoodNameCart.setText(food.name);
        holder.tvFoodPriceCart.setText(moneyFormat.format(food.price) + " " + "VND");
        ImageAPI.getCorner(food.thumbnail, holder.imgFoodCart);
        holder.edtQuantity.setText(food.quantity + " ");
        holder.btnCross.setOnClickListener(v -> {
            Utilities.api.addCart(food.id);
            try {
                mCart = Utilities.api.getCart();
            } catch (Exception e) {
                e.printStackTrace();
            }
            notifyDataSetChanged();
            callback.call();
        });
        holder.btnMinus.setOnClickListener(v -> {
            Utilities.api.removeInCart(food.id);
            try {
                mCart = Utilities.api.getCart();
            } catch (Exception e) {
                e.printStackTrace();
            }
            notifyDataSetChanged();
            callback.call();
        });
    }

    @Override
    public int getItemCount() {
        return mCart.size();
    }

    public class ViewHolderCart extends RecyclerView.ViewHolder {
        ImageView imgFoodCart;
        TextView tvFoodNameCart, tvFoodPriceCart;
        EditText edtQuantity;
        ImageButton btnMinus, btnCross;

        public ViewHolderCart(@NonNull View itemView) {
            super(itemView);
            edtQuantity = itemView.findViewById(R.id.edtQuantity);
            imgFoodCart = (ImageView) itemView.findViewById(R.id.img_food_cart);
            tvFoodNameCart = (TextView) itemView.findViewById(R.id.tv_food_name_cart);
            tvFoodPriceCart = (TextView) itemView.findViewById(R.id.tv_food_price_cart);
            btnCross = (ImageButton) itemView.findViewById(R.id.btn_cross);
            btnMinus = (ImageButton) itemView.findViewById(R.id.btn_minus);
        }
    }
}
