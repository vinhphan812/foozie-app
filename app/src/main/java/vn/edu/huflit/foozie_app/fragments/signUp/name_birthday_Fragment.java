package vn.edu.huflit.foozie_app.fragments.signUp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.Date;

import vn.edu.huflit.foozie_app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link name_birthday_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class name_birthday_Fragment extends Fragment {
    Button btnNext;
    NavController navController;
    TextInputLayout firstName, lastName;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public name_birthday_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment name_birthday_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static name_birthday_Fragment newInstance(String param1, String param2) {
        name_birthday_Fragment fragment = new name_birthday_Fragment();
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
        return inflater.inflate(R.layout.fragment_name_birthday_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = new Bundle();
        navController = Navigation.findNavController(view);
        btnNext = view.findViewById(R.id.btn_next_signUp);
        firstName = view.findViewById(R.id.edt_first_name_signUp);
        lastName = view.findViewById(R.id.edt_last_name_signUp);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FirstName = firstName.getEditText().getText().toString();
                String LastName = lastName.getEditText().getText().toString();

                if (FirstName.isEmpty() || LastName.isEmpty()) {
                    Snackbar.make(view, "Vui lòng nhập đầy đủ thông tin!", Snackbar.LENGTH_LONG).show();
                    return;
                } else {
                    bundle.putString("first_name", FirstName);
                    bundle.putString("last_name", LastName);
                }
                navController.navigate(R.id.action_name_birthday_Fragment_to_phone_address_Fragment, bundle);
            }
        });
    }

}