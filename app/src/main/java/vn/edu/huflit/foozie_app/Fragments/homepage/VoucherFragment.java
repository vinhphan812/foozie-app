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

import java.util.ArrayList;
import java.util.List;

import vn.edu.huflit.foozie_app.Adapters.VoucherAdapter;
import vn.edu.huflit.foozie_app.DetailVoucherActivity;
import vn.edu.huflit.foozie_app.Models.Voucher;
import vn.edu.huflit.foozie_app.R;
import vn.edu.huflit.foozie_app.Utils.Utilities;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VoucherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VoucherFragment extends Fragment implements VoucherAdapter.Listener {
    RecyclerView rvVouchers;
    VoucherAdapter voucherAdapter;
    List<Voucher> vouchers;
    View root;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VoucherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VoucherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VoucherFragment newInstance(String param1, String param2) {
        VoucherFragment fragment = new VoucherFragment();
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
        return inflater.inflate(R.layout.fragment_voucher, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        root = view;
        rvVouchers = view.findViewById(R.id.rv_voucher);

        vouchers = new ArrayList<>();

        voucherAdapter = new VoucherAdapter(vouchers, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvVouchers.setLayoutManager(linearLayoutManager);
        rvVouchers.setAdapter(voucherAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            vouchers = Utilities.api.getVoucherPublic();
            voucherAdapter.list = vouchers;
            voucherAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            Utilities.alert(root, e.getMessage(), Utilities.AlertType.Error);
        }
    }

    @Override
    public void onClick(Voucher item) {
        Intent intent = new Intent(getContext(), DetailVoucherActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", item);
        bundle.putString("id", item.id);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}