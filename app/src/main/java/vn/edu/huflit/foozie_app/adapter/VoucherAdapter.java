package vn.edu.huflit.foozie_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.internal.http2.Http2Connection;
import vn.edu.huflit.foozie_app.Models.Voucher;
import vn.edu.huflit.foozie_app.R;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.ViewHolderVoucher> {
    private List<Voucher> mvouchers;
    private VoucherAdapter.Listener mlistener;

    public VoucherAdapter(List<Voucher> mvouchers, Listener listener) {
        this.mvouchers = mvouchers;
        this.mlistener = listener;
    }

    public interface Listener {
        void onClick(Voucher voucherItem);
    }

    @NonNull
    @Override
    public VoucherAdapter.ViewHolderVoucher onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voucher, parent, false);
        return new VoucherAdapter.ViewHolderVoucher(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderVoucher holder, int position) {
        Voucher voucher = mvouchers.get(position);
        ViewHolderVoucher viewHolderVoucher = (ViewHolderVoucher) holder;
        viewHolderVoucher.tvNameVoucher.setText(voucher.name);
        viewHolderVoucher.tvCodeVoucher.setText(voucher.code);
        viewHolderVoucher.tvDateVoucher.setText(voucher.valid_date.toString());
        viewHolderVoucher.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.onClick(voucher);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mvouchers.size();
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
