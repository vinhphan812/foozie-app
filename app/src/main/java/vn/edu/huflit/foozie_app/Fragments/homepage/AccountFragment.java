package vn.edu.huflit.foozie_app.Fragments.homepage;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import vn.edu.huflit.foozie_app.Models.User;
import vn.edu.huflit.foozie_app.R;
import vn.edu.huflit.foozie_app.Utils.Utilities;
import vn.edu.huflit.foozie_app.SignInActivity;
import vn.edu.huflit.foozie_app.verifyAccountActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {
    User newUser;
    TextView fullName, phone, email;
    ImageView rank;
    ConstraintLayout btnChangePass, btnLogOut, btnEdit;
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
        fullName = (TextView) view.findViewById(R.id.tv_full_name_user);
        phone = (TextView) view.findViewById(R.id.tv_phone_user);
        email = (TextView) view.findViewById(R.id.tv_email_user);
        rank = (ImageView) view.findViewById(R.id.img_rank_user);
        try {
            newUser = Utilities.api.getMe();
            fullName.setText(newUser.first_name + ' ' + newUser.last_name);
            phone.setText(newUser.phone);
            email.setText(newUser.email);
//            ImageAPI.loadBackground(newUser.ranking);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //change pass
        btnChangePass = (ConstraintLayout) view.findViewById(R.id.btn_change_pass);
        btnChangePass.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), verifyAccountActivity.class);
            startActivity(intent);
        });
        //log out
        btnLogOut = (ConstraintLayout) view.findViewById(R.id.btn_log_out);
        btnLogOut.setOnClickListener(v -> {
            Utilities.api.Logout();
            Intent intentLogOut = new Intent(v.getContext(), SignInActivity.class);
            startActivity(intentLogOut);
        });
        //edit
        btnEdit = (ConstraintLayout) view.findViewById(R.id.btn_edit);
        btnEdit.setOnClickListener(v -> {
            showChangePasswordDialog();
        });
    }

    private void showChangePasswordDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_edit, null);
        TextInputLayout edtFullName, edtPhone, edtEmail;
        Button btnUpdateInfo;
        edtFullName = (TextInputLayout) view.findViewById(R.id.edt_full_name);
        edtPhone = (TextInputLayout) view.findViewById(R.id.edt_phone);
        edtEmail = view.findViewById(R.id.edt_email);
        btnUpdateInfo = (Button) view.findViewById(R.id.btn_edit);
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_edit);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
}