package vn.edu.huflit.foozie_app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import vn.edu.huflit.foozie_app.Models.Order;
import vn.edu.huflit.foozie_app.Models.User;
import vn.edu.huflit.foozie_app.Models.Voucher;
import vn.edu.huflit.foozie_app.R;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolderOrder> {
    public List<Order> mlist;
    private OrderAdapter.Listener mlistener;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
    private DecimalFormat moneyFormat = new DecimalFormat("0.00");
    public OrderAdapter(List<Order> list, Listener mlistener) {
        this.mlist = list;
        this.mlistener = mlistener;
    }

    @NonNull
    @Override
    public ViewHolderOrder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderAdapter.ViewHolderOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderOrder holder, int position) {
        Order order = mlist.get(position);
        OrderAdapter.ViewHolderOrder viewHolderOrder = holder;
        viewHolderOrder.dateOrder.setText(dateFormat.format(order.order_date));
        viewHolderOrder.idOrder.setText(order.id);
        viewHolderOrder.totalPriceOrder.setText(moneyFormat.format(order.total) + " " + "VND");
        viewHolderOrder.itemView.setOnClickListener(v -> {
            mlistener.onClick(order);
        });
    }


    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public interface Listener {
        void onClick(Order order);
    }

    public class ViewHolderOrder extends RecyclerView.ViewHolder {
        TextView dateOrder, idOrder, totalPriceOrder;

        public ViewHolderOrder(@NonNull View itemView) {
            super(itemView);
            dateOrder = itemView.findViewById(R.id.tv_order_date);
            idOrder = itemView.findViewById(R.id.tv_order_id);
            totalPriceOrder = itemView.findViewById(R.id.tv_order_total_prices);
        }
    }
}