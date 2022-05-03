package vn.edu.huflit.foozie_app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.huflit.foozie_app.Models.FoodType;
import vn.edu.huflit.foozie_app.R;

public class TypeFoodAdapter extends RecyclerView.Adapter<TypeFoodAdapter.ViewHolderTypeFood> {
    private List<FoodType> mtype;
    private TypeFoodAdapter.Listener mlistener;

    public interface Listener {
        void onClick(FoodType typeItem);
    }

    public TypeFoodAdapter(List<FoodType> mtype, Listener mlistener) {
        this.mtype = mtype;
        this.mlistener = mlistener;
    }

    @NonNull
    @Override
    public ViewHolderTypeFood onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_type_food, parent, false);
        return new TypeFoodAdapter.ViewHolderTypeFood(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTypeFood holder, int position) {
        FoodType foodType = mtype.get(position);
        ViewHolderTypeFood viewHolderTypeFood = (ViewHolderTypeFood) holder;
        viewHolderTypeFood.tvFoodType.setText(foodType.name);
        viewHolderTypeFood.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.onClick(foodType);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mtype.size();
    }

    public class ViewHolderTypeFood extends RecyclerView.ViewHolder {
        TextView tvFoodType;

        public ViewHolderTypeFood(@NonNull View itemView) {
            super(itemView);
            tvFoodType = itemView.findViewById(R.id.tv_food_type);
        }
    }
}
