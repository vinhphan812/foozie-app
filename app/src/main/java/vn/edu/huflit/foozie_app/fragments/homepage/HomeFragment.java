package vn.edu.huflit.foozie_app.fragments.homepage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.edu.huflit.foozie_app.DetailFoodsActivity;
import vn.edu.huflit.foozie_app.DetailsFoodTypeActivity;
import vn.edu.huflit.foozie_app.LocationActivity;
import vn.edu.huflit.foozie_app.Models.Food;
import vn.edu.huflit.foozie_app.Models.FoodType;
import vn.edu.huflit.foozie_app.R;
import vn.edu.huflit.foozie_app.Utils.Utilities;
import vn.edu.huflit.foozie_app.adapter.FoodAdapter;
import vn.edu.huflit.foozie_app.adapter.TypeFoodAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements TypeFoodAdapter.Listener,FoodAdapter.Listener {
    RecyclerView rvTypeFood,rvFood;
    TypeFoodAdapter typeFoodAdapter;
    FoodAdapter foodAdapter;
    List<FoodType> foodTypes;
    List<Food> foods;
    ConstraintLayout btnLocation;
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
        //type_food
        rvTypeFood = view.findViewById(R.id.rv_food_type);
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
        rvFood=view.findViewById(R.id.rv_food);
        try {
            foods=Utilities.api.getFoods();
            foodAdapter = new FoodAdapter(foods, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            rvFood.setLayoutManager(linearLayoutManager);
            rvFood.setAdapter(foodAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //location
        btnLocation=(ConstraintLayout) view.findViewById(R.id.btn_location);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.getContext(), LocationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(FoodType typeItem) {
        Intent intent = new Intent(getContext(), DetailsFoodTypeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("typeItem", typeItem);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(Food foodsItem) {
        Intent intent = new Intent(getContext(), DetailFoodsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("foodsItem", foodsItem);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}