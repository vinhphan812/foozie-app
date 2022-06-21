package vn.edu.huflit.foozie_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import vn.edu.huflit.foozie_app.Models.Voucher;
import vn.edu.huflit.foozie_app.R;
import vn.edu.huflit.foozie_app.Utils.Callback;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.ViewHolderVoucher> {
    public List<Voucher> list;
    private VoucherAdapter.Listener mlistener;
    private int TYPE_LAYOUT;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");

    public VoucherAdapter(List<Voucher> list, Listener listener, int TYPE_LAYOUT) {
        this.list = list;
        this.mlistener = listener;
        this.TYPE_LAYOUT = TYPE_LAYOUT;
    }

    public interface Listener {
        void onClick(Voucher voucherItem);
    }

    public interface VoucherClickListener {
        void onClick(View view, int position);
    }

    @NonNull
    @Override
    public VoucherAdapter.ViewHolderVoucher onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        if (TYPE_LAYOUT == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voucher, parent, false);
            return new ViewHolderVoucher(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_voucher, parent, false);
            return new ViewHolderMyVoucher(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderVoucher holder, int position) {
        Voucher voucher = list.get(position);
        if (TYPE_LAYOUT == 1) {
            ViewHolderVoucher viewHolderVoucher = holder;
            viewHolderVoucher.tvNameVoucher.setText(voucher.name);
            viewHolderVoucher.tvCodeVoucher.setText(voucher.code);
            viewHolderVoucher.tvDateVoucher.setText(dateFormat.format(voucher.valid_date));
            viewHolderVoucher.itemView.setOnClickListener(v -> {
                mlistener.onClick(voucher);
            });
        } else {
            ViewHolderMyVoucher viewHolderMyVoucher = (ViewHolderMyVoucher) holder;
            viewHolderMyVoucher.tvNameMyVoucher.setText(voucher.name);
            viewHolderMyVoucher.tvCodeMyVoucher.setText(voucher.code);
            viewHolderMyVoucher.tvDateMyVoucher.setText(dateFormat.format(voucher.valid_date));
            HandleBackgroundVoucher(viewHolderMyVoucher, position);
            ((ViewHolderMyVoucher) holder).setItemClickListener(new VoucherClickListener() {
                @Override
                public void onClick(View view, int position) {
                    list.get(position).HandleClick();
                    for (int i = 0; i < list.size(); i++) {
                        if (i != position && list.get(i).getClicked()) {
                            list.get(i).setClicked(false);
                        }
                    }
                    mlistener.onClick(list.get(position));
                    notifyDataSetChanged();
                }
            });
        }
    }

    private void HandleBackgroundVoucher(ViewHolderMyVoucher view, int position) {
        if (list.get(position).getClicked()) {
            view.itemView.setBackgroundResource(R.drawable.layout_bg_item_selected);
        } else {
            view.itemView.setBackgroundResource(R.drawable.layout_bg_item);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderVoucher extends RecyclerView.ViewHolder {
        ImageView imgIconVoucher;
        TextView tvNameVoucher, tvCodeVoucher, tvDateVoucher;

        public ViewHolderVoucher(View view) {
            super(view);
            imgIconVoucher = view.findViewById(R.id.img_icon_voucher);
            tvNameVoucher = view.findViewById(R.id.tv_name_voucher);
            tvCodeVoucher = view.findViewById(R.id.tv_code_voucher);
            tvDateVoucher = view.findViewById(R.id.tv_date_voucher);
        }
    }

    public class ViewHolderMyVoucher extends ViewHolderVoucher implements View.OnClickListener {
        ImageView imgIconMyVoucher;
        TextView tvNameMyVoucher, tvCodeMyVoucher, tvDateMyVoucher;
        private VoucherClickListener voucherClickListener;

        public ViewHolderMyVoucher(View view) {
            super(view);
            imgIconMyVoucher = view.findViewById(R.id.img_icon_my_voucher);
            tvNameMyVoucher = view.findViewById(R.id.tv_name_my_voucher);
            tvCodeMyVoucher = view.findViewById(R.id.tv_code_my_voucher);
            tvDateMyVoucher = view.findViewById(R.id.tv_date_my_voucher);
            view.setOnClickListener(this);
        }

        public void setItemClickListener(VoucherClickListener voucherClickListener) {
            this.voucherClickListener = voucherClickListener;
        }

        @Override
        public void onClick(View v) {
            voucherClickListener.onClick(v, getAdapterPosition());
        }
    }

}
