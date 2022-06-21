package vn.edu.huflit.foozie_app.Fragments.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import vn.edu.huflit.foozie_app.Adapters.FoodAdapter;
import vn.edu.huflit.foozie_app.Adapters.TypeFoodAdapter;
import vn.edu.huflit.foozie_app.CartActivity;
import vn.edu.huflit.foozie_app.DetailFoodsActivity;
import vn.edu.huflit.foozie_app.Models.Food;
import vn.edu.huflit.foozie_app.Models.FoodType;
import vn.edu.huflit.foozie_app.R;
import vn.edu.huflit.foozie_app.Utils.Utilities;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements TypeFoodAdapter.Listener, FoodAdapter.Listener {
    RecyclerView rvTypeFood, rvFood;
    TypeFoodAdapter typeFoodAdapter;
    FoodAdapter foodAdapter;
    List<FoodType> foodTypes;
    List<Food> foods;
    SearchView searchView;
    ImageButton btnCart;
    TextView tvBadge;
    ConstraintLayout badge;
    int countBadge;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvBadge = view.findViewById(R.id.tv_badge_count_cart);
        badge = view.findViewById(R.id.badge);
        try {
            countBadge = Utilities.api.getCart().size();
            if (countBadge != 0) {
                tvBadge.setText(countBadge + "");
            } else {
                badge.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //type_food
        rvTypeFood = view.findViewById(R.id.rv_food_type);
        searchView = view.findViewById(R.id.searchView);
        try {
            foodTypes = Utilities.api.getFoodTypes();
            typeFoodAdapter = new TypeFoodAdapter(foodTypes, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            rvTypeFood.setLayoutManager(linearLayoutManager);
            rvTypeFood.setAdapter(typeFoodAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //food
        rvFood = view.findViewById(R.id.rv_food);
        try {
            foods = Utilities.api.getFoods(null, null);
            foodAdapter = new FoodAdapter(foods, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            rvFood.setLayoutManager(linearLayoutManager);
            rvFood.setAdapter(foodAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //search
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                try {
                    foodAdapter.mfoods = Utilities.api.getFoods(typeFoodAdapter.selected, query);
                    foodAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Utilities.alert(getView(), e.getMessage(), Utilities.AlertType.Error);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        //cart
        btnCart = (ImageButton) view.findViewById(R.id.btn_cart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CartActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(FoodType foodType) {
        try {
            foodAdapter.mfoods = Utilities.api.getFoods(foodType.id, null);
            typeFoodAdapter.selected = foodType.id;
            typeFoodAdapter.notifyDataSetChanged();
            foodAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            Utilities.alert(getView(), e.getMessage(), Utilities.AlertType.Error);
        }
    }

    @Override
    public void onClick(Food foodsItem) {
        Intent intent = new Intent(getContext(), DetailFoodsActivity.class);
        intent.putExtra("id", foodsItem.id);
        startActivity(intent);
    }
}