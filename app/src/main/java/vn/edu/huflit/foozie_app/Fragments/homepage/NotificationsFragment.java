package vn.edu.huflit.foozie_app.Fragments.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.huflit.foozie_app.Adapters.NotificationAdapter;
import vn.edu.huflit.foozie_app.Adapters.NotifyAdapter;
import vn.edu.huflit.foozie_app.Adapters.VoucherAdapter;
import vn.edu.huflit.foozie_app.CreateOrderActivity;
import vn.edu.huflit.foozie_app.Models.Notification;
import vn.edu.huflit.foozie_app.Models.Voucher;
import vn.edu.huflit.foozie_app.MyVoucherActivity;
import vn.edu.huflit.foozie_app.R;
import vn.edu.huflit.foozie_app.SignInActivity;
import vn.edu.huflit.foozie_app.Utils.Utilities;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationsFragment extends Fragment implements NotificationAdapter.Listener {
    RecyclerView rvNotification;
    NotificationAdapter notificationAdapter;
    List<Notification> notifications;
    NotifyAdapter notifyAdapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotificationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationsFragment newInstance(String param1, String param2) {
        NotificationsFragment fragment = new NotificationsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvNotification = view.findViewById(R.id.rv_notification);
        try {
            notifications = Utilities.api.getNotifications();
            notificationAdapter = new NotificationAdapter(notifications, this);

//            notifyAdapter = new NotifyAdapter(notifications);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            rvNotification.setLayoutManager(linearLayoutManager);
            rvNotification.setAdapter(notificationAdapter);
        } catch (Exception e) {
            if (e.getMessage() == "FAIL_AUTHENTICATION") {
                Intent intent = new Intent(getContext(), SignInActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onClick(Notification notification) {

    }
}