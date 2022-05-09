package vn.edu.huflit.foozie_app.Fragments.changePass;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import vn.edu.huflit.foozie_app.R;
import vn.edu.huflit.foozie_app.SignInActivity;
import vn.edu.huflit.foozie_app.Utils.Utilities;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link changePasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class changePasswordFragment extends Fragment {
    Button btnChangePass;
    NavController navController;
    TextInputLayout pass, confirm;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public changePasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment changePasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static changePasswordFragment newInstance(String param1, String param2) {
        changePasswordFragment fragment = new changePasswordFragment();
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
        return inflater.inflate(R.layout.fragment_change_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        btnChangePass = view.findViewById(R.id.btn_change_pass_verify);
        pass = view.findViewById(R.id.edt_pass_verify);
        confirm = view.findViewById(R.id.edt_confirm_verify);
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Pass = pass.getEditText().getText().toString();
                String Confirm = confirm.getEditText().getText().toString();
                if (Pass.isEmpty() || Confirm.isEmpty()) {
                    Utilities.alert(view, "Vui lòng nhập đầy đủ thông tin!", Utilities.AlertType.Error);
                    return;
                }
                if (Pass.length() < 10) {
                    Utilities.alert(view, "Vui lòng nhập mật khẩu 10 ký tự!", Utilities.AlertType.Error);
                    return;
                }
                if (!Pass.equals(Confirm)) {
                    Utilities.alert(view, "Mật khẩu không trùng khớp!", Utilities.AlertType.Error);
                    return;
                }
                Intent intent = new Intent(v.getContext(), SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}