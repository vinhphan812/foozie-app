package vn.edu.huflit.foozie_app.Fragments.signUp;

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
import vn.edu.huflit.foozie_app.Utils.Utilities;
import vn.edu.huflit.foozie_app.SignInActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserAndPassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserAndPassFragment extends Fragment {
    Button btnRegister;
    NavController navController;
    TextInputLayout username, password, confirm;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserAndPassFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment usernme_password_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserAndPassFragment newInstance(String param1, String param2) {
        UserAndPassFragment fragment = new UserAndPassFragment();
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
        return inflater.inflate(R.layout.fragment_usernme_password_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        username = view.findViewById(R.id.edt_user_name_signUp);
        password = view.findViewById(R.id.edt_password_signUp);
        confirm = view.findViewById(R.id.edt_confirm_password_signUp);
        btnRegister = (Button) view.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(v -> {
            String Firstname = getArguments().getString("first_name");
            String Lastname = getArguments().getString("last_name");
            String Phone = getArguments().getString("phone");
            String Email = getArguments().getString("email");
            String Username = username.getEditText().getText().toString();
            String Password = password.getEditText().getText().toString();
            String Confirm = confirm.getEditText().getText().toString();
            if (Username.isEmpty() || Password.isEmpty() || Confirm.isEmpty()) {
                Utilities.alert(view, "Vui lòng nhập đầy đủ thông tin!", Utilities.AlertType.Error);
                return;
            }
            if (!Confirm.equals(Password)) {
                Utilities.alert(view, "Mật khẩu không trùng khớp!", Utilities.AlertType.Error);
                return;
            }
            if (Password.length() < 8) {
                Utilities.alert(view, "Vui lòng nhập mật khẩu 8 ký tự!", Utilities.AlertType.Error);
                return;
            }
            try {
                Utilities.api.SignUp(Username, Password, Firstname, Lastname, Email, Phone);
                Intent intent = new Intent(getContext(), SignInActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                Utilities.alert(v,"Hệ thống đang gặp sự cố", Utilities.AlertType.Error);
            }
        });
    }
}