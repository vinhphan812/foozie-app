package vn.edu.huflit.foozie_app.Adapters;

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

    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");

    public VoucherAdapter(List<Voucher> list, Listener listener) {
        this.list = list;
        this.mlistener = listener;
    }

    public interface Listener {
        void onClick(Voucher voucherItem);
    }

    @NonNull
    @Override
    public VoucherAdapter.ViewHolderVoucher onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voucher, parent, false);
        return new ViewHolderVoucher(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderVoucher holder, int position) {
        Voucher voucher = list.get(position);
        ViewHolderVoucher viewHolderVoucher = (ViewHolderVoucher) holder;
        viewHolderVoucher.tvNameVoucher.setText(voucher.name);
        viewHolderVoucher.tvCodeVoucher.setText(voucher.code);
        viewHolderVoucher.tvDateVoucher.setText(dateFormat.format(voucher.valid_date));
        viewHolderVoucher.itemView.setOnClickListener(v -> mlistener.onClick(voucher));
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
}
