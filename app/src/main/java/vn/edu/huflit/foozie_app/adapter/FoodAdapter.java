package vn.edu.huflit.foozie_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.huflit.foozie_app.Models.Food;
import vn.edu.huflit.foozie_app.R;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolderFood> {


    @NonNull
    @Override
    public ViewHolderFood onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFood holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderFood extends RecyclerView.ViewHolder {
        public ViewHolderFood(@NonNull View itemView) {
            super(itemView);
        }
    }
}
