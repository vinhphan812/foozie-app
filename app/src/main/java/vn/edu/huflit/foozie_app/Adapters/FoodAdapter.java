package vn.edu.huflit.foozie_app.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
