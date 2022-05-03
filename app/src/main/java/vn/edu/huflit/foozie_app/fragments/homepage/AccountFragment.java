package vn.edu.huflit.foozie_app.fragments.homepage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.internal.cache.DiskLruCache;
import vn.edu.huflit.foozie_app.Models.Food;
import vn.edu.huflit.foozie_app.Models.User;
import vn.edu.huflit.foozie_app.R;
import vn.edu.huflit.foozie_app.Utils.Utilities;
import vn.edu.huflit.foozie_app.activity_signin;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {
    Button btnLogOut;
    TextView Name, Email, Phone, Date;
    User user;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogOut = (Button) view.findViewById(R.id.btn_log_out);
        Name = view.findViewById(R.id.tv_name_user);
        Email = view.findViewById(R.id.tv_email_user);
        Phone = view.findViewById(R.id.tv_phone_user);
        Date = view.findViewById(R.id.tv_join_date_user);
        try {
            user = Utilities.api.getMe();
            Name.setText(user.first_name + " " + user.last_name);
            Email.setText(user.email);
            Phone.setText(user.phone);
            Date.setText(user.created_at);

        } catch (Exception e) {
            e.printStackTrace();
        }
        btnLogOut.setOnClickListener(v -> {
            Utilities.api.Logout();
            Intent intent = new Intent(view.getContext(), activity_signin.class);
            startActivity(intent);
        });
    }
}