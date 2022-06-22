package vn.edu.huflit.foozie_app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import vn.edu.huflit.foozie_app.Models.Notification;
import vn.edu.huflit.foozie_app.Models.Order;
import vn.edu.huflit.foozie_app.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolderNotification> {
    public List<Notification> mlist;
    private NotificationAdapter.Listener mlistener;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");

    public NotificationAdapter(List<Notification> mlist, Listener mlistener) {
        this.mlist = mlist;
        this.mlistener = mlistener;
    }

    @NonNull
    @Override
    public ViewHolderNotification onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new NotificationAdapter.ViewHolderNotification(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNotification holder, int position) {
        Notification notification = mlist.get(position);

        String cur = dateFormat.format(new Date());
        String date = dateFormat.format(notification.date);

        holder.dateNotification.setText(cur.equals(date) ? hourFormat.format(notification.date) : date);
        holder.titleNotification.setText(notification.title);
        holder.contentNotification.setText(notification.body);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.onClick(notification);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public interface Listener {
        void onClick(Notification notification);
    }

    public class ViewHolderNotification extends RecyclerView.ViewHolder {
        TextView dateNotification, titleNotification, contentNotification;

        public ViewHolderNotification(@NonNull View itemView) {
            super(itemView);
            dateNotification = itemView.findViewById(R.id.date);
            titleNotification = itemView.findViewById(R.id.title);
            contentNotification = itemView.findViewById(R.id.body);
        }
    }
}
