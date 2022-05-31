package vn.edu.huflit.foozie_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import vn.edu.huflit.foozie_app.Models.Voucher;
import vn.edu.huflit.foozie_app.R;

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

    @NonNull
    @Override
    public VoucherAdapter.ViewHolderVoucher onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        if (TYPE_LAYOUT == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voucher, parent, false);
            return new ViewHolderVoucher(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voucher, parent, false);
            return new ViewHolderMyVoucher(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderVoucher holder, int position) {
        Voucher voucher = list.get(position);
        if (TYPE_LAYOUT==1){
            ViewHolderVoucher viewHolderVoucher = holder;
            viewHolderVoucher.tvNameVoucher.setText(voucher.name);
            viewHolderVoucher.tvCodeVoucher.setText(voucher.code);
            viewHolderVoucher.tvDateVoucher.setText(dateFormat.format(voucher.valid_date));
            viewHolderVoucher.itemView.setOnClickListener(v -> mlistener.onClick(voucher));
        }
        else {
            ViewHolderMyVoucher viewHolderMyVoucher = (ViewHolderMyVoucher) holder;
            viewHolderMyVoucher.tvNameVoucher.setText(voucher.name);
            viewHolderMyVoucher.tvCodeVoucher.setText(voucher.code);
            viewHolderMyVoucher.tvDateVoucher.setText(dateFormat.format(voucher.valid_date));
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

    private class ViewHolderMyVoucher extends ViewHolderVoucher {
        ImageView imgIconVoucher;
        TextView tvNameVoucher, tvCodeVoucher, tvDateVoucher;

        public ViewHolderMyVoucher(View view) {
            super(view);
            imgIconVoucher = view.findViewById(R.id.img_icon_voucher);
            tvNameVoucher = view.findViewById(R.id.tv_name_voucher);
            tvCodeVoucher = view.findViewById(R.id.tv_code_voucher);
            tvDateVoucher = view.findViewById(R.id.tv_date_voucher);
        }
    }
}
