package com.example.customeasy;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    // UI elements
    EditText updateId, updateName, updateAddress, updatePhone;
    Button fetchBtn, updateBtn;

    // Database object
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // Link views
        updateId = findViewById(R.id.updateId);
        updateName = findViewById(R.id.updateName);
        updateAddress = findViewById(R.id.updateAddress);
        updatePhone = findViewById(R.id.updatePhone);
        fetchBtn = findViewById(R.id.fetchBtn);
        updateBtn = findViewById(R.id.updateBtn);

        // Initialize DB
        dbHelper = new DBHelper(this);

        // Fetch existing data
        fetchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idText = updateId.getText().toString().trim();

                if (idText.isEmpty()) {
                    Toast.makeText(UpdateActivity.this, "Enter ID first", Toast.LENGTH_SHORT).show();
                    return;
                }

                int id = Integer.parseInt(idText);
                Cursor cursor = dbHelper.getCustomer(id);

                if (cursor.getCount() == 0) {
                    Toast.makeText(UpdateActivity.this, "No customer found", Toast.LENGTH_SHORT).show();
                    return;
                }

                cursor.moveToFirst();
                updateName.setText(cursor.getString(1));
                updateAddress.setText(cursor.getString(2));
                updatePhone.setText(cursor.getString(3));
            }
        });

        // Update data in DB
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idText = updateId.getText().toString().trim();
                String name = updateName.getText().toString().trim();
                String address = updateAddress.getText().toString().trim();
                String phone = updatePhone.getText().toString().trim();

                if (idText.isEmpty() || name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(UpdateActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                int id = Integer.parseInt(idText);
                boolean updated = dbHelper.updateCustomer(id, name, address, phone);

                if (updated) {
                    Toast.makeText(UpdateActivity.this, "Customer updated!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
