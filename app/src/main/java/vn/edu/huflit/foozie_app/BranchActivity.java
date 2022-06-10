package vn.edu.huflit.foozie_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import vn.edu.huflit.foozie_app.Adapters.BranchesAdapter;
import vn.edu.huflit.foozie_app.Adapters.FoodAdapter;
import vn.edu.huflit.foozie_app.Models.Branch;
import vn.edu.huflit.foozie_app.Utils.Utilities;

public class BranchActivity extends AppCompatActivity {
    RecyclerView rvBranch;
    BranchesAdapter branchesAdapter;
    List<Branch> branches;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        bindWidget();
        setUpWidgetListener();
        callAPI();
    }

    private void callAPI() {
        try {
            branches = Utilities.api.getBranches();
            branchesAdapter = new BranchesAdapter(branches);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rvBranch.setLayoutManager(linearLayoutManager);
            rvBranch.setAdapter(branchesAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpWidgetListener() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void bindWidget() {
        rvBranch = findViewById(R.id.rv_branches);
        btnBack = findViewById(R.id.btn_back_branch);
    }
}