package vn.edu.huflit.foozie_app.Fragments.homepage;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import vn.edu.huflit.foozie_app.API.ImageAPI;
import vn.edu.huflit.foozie_app.BranchActivity;
import vn.edu.huflit.foozie_app.Models.Food;
import vn.edu.huflit.foozie_app.Models.Order;
import vn.edu.huflit.foozie_app.Models.Ranking;
import vn.edu.huflit.foozie_app.Models.User;
import vn.edu.huflit.foozie_app.Models.Voucher;
import vn.edu.huflit.foozie_app.MyOrderActivity;
import vn.edu.huflit.foozie_app.MyVoucherActivity;
import vn.edu.huflit.foozie_app.R;
import vn.edu.huflit.foozie_app.SignInActivity;
import vn.edu.huflit.foozie_app.Utils.Utilities;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {
    User newUser;
    TextView fullName, phone, email, countOrder, countMyVoucher;
    ConstraintLayout btnChangePass, btnLogOut, btnEdit, btnBranch;
    List<Voucher> myVoucher;
    LinearLayout btnMyVoucher, btnMyOrder;
    List<Order> myOrder;
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
        countOrder = view.findViewById(R.id.tv_count_order);
        countMyVoucher = view.findViewById(R.id.tv_count_my_voucher);
        btnMyVoucher = view.findViewById(R.id.layout_voucher);
        btnMyOrder = view.findViewById(R.id.layout_order);

        //My Order
        try {
            myOrder = Utilities.api.getHistoryOrders();
            countOrder.setText(myOrder.size() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        btnMyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyOrderActivity.class);
                startActivity(intent);
            }
        });

        //My Voucher
        try {
            myVoucher = Utilities.api.getMyVouchers();
            countMyVoucher.setText(myVoucher.size() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        btnMyVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyVoucherActivity.class);
                startActivity(intent);
            }
        });
        // information account
        try {
            newUser = Utilities.api.getMe();
            fullName.setText(newUser.first_name + ' ' + newUser.last_name);
            phone.setText(newUser.phone);
            email.setText(newUser.email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //change pass
        btnChangePass = (ConstraintLayout) view.findViewById(R.id.btn_change_pass);
        btnChangePass.setOnClickListener(v -> {
            showChangePassDialog();
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
            showChangeInfoDialog();
            return;
        });
        //branch
        btnBranch = (ConstraintLayout) view.findViewById(R.id.btn_branch);
        btnBranch.setOnClickListener(v -> {
            Intent intentBranch = new Intent(getContext(), BranchActivity.class);
            startActivity(intentBranch);
        });
    }

    private void showChangePassDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_change_pass, null);

        TextInputLayout edtOldPass, edtNewPass;
        Button btnChangePass;

        edtOldPass = view.findViewById(R.id.edt_old_pass);
        edtNewPass = view.findViewById(R.id.edt_new_pass);
        btnChangePass = (Button) view.findViewById(R.id.btn_change_pass_dialog);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String oldPass = edtOldPass.getEditText().getText().toString();
                    String newPass = edtNewPass.getEditText().getText().toString();
                    if (oldPass.isEmpty() || newPass.isEmpty()) {
                        Utilities.alert(view, "Vui lòng nhập đầy đủ thông tin", Utilities.AlertType.Error);
                    }
                    if (!oldPass.equals(newPass)) {
                        Utilities.alert(view, "Mật khẩu đã tồn tại trước đó", Utilities.AlertType.Error);
                    }
                    if (newPass.length() < 8) {
                        Utilities.alert(view, "Vui lòng nhập mật khẩu 8 ký tự!", Utilities.AlertType.Error);
                    }
                    Utilities.api.ChangePassword(oldPass, newPass);
                    Utilities.alert(view, "Thành công", Utilities.AlertType.Success);
                } catch (Exception e) {
                    Utilities.alert(view, "Thất bại", Utilities.AlertType.Error);
                }
            }
        });
        dialog.show();
    }

    private void showChangeInfoDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_edit, null);

        TextInputLayout edtFullName, edtPhone;
        Button btnUpdateInfo;

        edtFullName = view.findViewById(R.id.edt_full_name_edit);
        edtPhone = view.findViewById(R.id.edt_phone_edit);
        btnUpdateInfo = (Button) view.findViewById(R.id.btn_change_info);
        try {
            newUser = Utilities.api.getMe();
            edtFullName.getEditText().setText(newUser.first_name + " " + newUser.last_name);
            edtPhone.getEditText().setText(newUser.phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = edtFullName.getEditText().getText().toString();
                String lastName = "";
                String firstName = "";
                if (fullName.split("\\w+").length > 1) {
                    lastName = fullName.substring(fullName.lastIndexOf(" ") + 1);
                    firstName = fullName.substring(0, fullName.lastIndexOf(' '));
                } else {
                    firstName = fullName;
                }
                String phone = edtPhone.getEditText().getText().toString();
                try {
                    Utilities.api.updateMe(firstName, lastName, phone);
                    Utilities.alert(view, "Thành công", Utilities.AlertType.Success);
                } catch (Exception e) {
                    Utilities.alert(view, "Thất bại", Utilities.AlertType.Error);
                }
            }
        });
        dialog.show();
    }
}