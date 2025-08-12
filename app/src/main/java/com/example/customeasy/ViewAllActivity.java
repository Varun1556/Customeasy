package com.example.customeasy;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelper dbHelper;
    List<CustomerModel> customerList;
    CustomerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        // Link RecyclerView from layout
        recyclerView = findViewById(R.id.recyclerView);

        // Initialize DB helper
        dbHelper = new DBHelper(this);

        // Load customer data from database
        customerList = dbHelper.getAllCustomerList();

        // Set RecyclerView layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create adapter and set it to RecyclerView
        adapter = new CustomerAdapter(ViewAllActivity.this, customerList);
        recyclerView.setAdapter(adapter);
    }
}
