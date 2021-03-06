package vn.edu.huflit.foozie_app.Fragments.signUp;

import android.os.Bundle;
import android.util.Log;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhoneAndAddressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhoneAndAddressFragment extends Fragment {
    Button btnNext;
    NavController navController;
    TextInputLayout phone, email;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PhoneAndAddressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment phone_addressFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhoneAndAddressFragment newInstance(String param1, String param2) {
        PhoneAndAddressFragment fragment = new PhoneAndAddressFragment();
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
        return inflater.inflate(R.layout.fragment_phone_address, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = new Bundle();
        btnNext = view.findViewById(R.id.btn_next_signUp2);
        navController = Navigation.findNavController(view);
        phone = view.findViewById(R.id.edt_phone_signUp);
        email = view.findViewById(R.id.edt_email_signUp);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Firstname = getArguments().getString("first_name");
                String Lastname = getArguments().getString("last_name");
                String Phone = phone.getEditText().getText().toString();
                String Email = email.getEditText().getText().toString();
                if (Phone.isEmpty()) {
                    Utilities.alert(view, "Vui l??ng nh???p ?????y ????? th??ng tin!", Utilities.AlertType.Error);
                    return;
                } else {
                    bundle.putString("first_name", Firstname);
                    bundle.putString("last_name", Lastname);
                    bundle.putString("phone", Phone);
                    bundle.putString("email", Email);
                }
                Log.d("a", String.valueOf(bundle));
                navController.navigate(R.id.action_phone_address_Fragment_to_usernme_password_Fragment, bundle);
            }
        });
    }
}