package vn.edu.huflit.foozie_app.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import vn.edu.huflit.foozie_app.Models.Branch;
import vn.edu.huflit.foozie_app.Models.Food;
import vn.edu.huflit.foozie_app.R;

public class BranchesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<Branch> mbranches;

    public BranchesAdapter(List<Branch> branches) {
        this.mbranches = branches;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_branch, parent, false);
        return new BranchesAdapter.ViewHolderBranch(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Branch branch = mbranches.get(position);
        BranchesAdapter.ViewHolderBranch viewHolderBranch = (BranchesAdapter.ViewHolderBranch) holder;
        viewHolderBranch.tvNameBranch.setText(branch.name);
        viewHolderBranch.tvAddressBranch.setText(branch.address);
        viewHolderBranch.tv_phone_branch.setText(branch.phone);
    }

    @Override
    public int getItemCount() {
        return mbranches.size();
    }

    private class ViewHolderBranch extends RecyclerView.ViewHolder {
        TextView tvNameBranch, tvAddressBranch, tv_phone_branch;

        public ViewHolderBranch(View view) {
            super(view);
            tvNameBranch = view.findViewById(R.id.tv_name_branch);
            tvAddressBranch = view.findViewById(R.id.tv_address_branch);
            tv_phone_branch = view.findViewById(R.id.tv_phone_branch);
        }
    }
}
