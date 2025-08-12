package com.example.customeasy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {

    // Declare input fields and button
    EditText nameInput, addressInput, phoneInput;
    Button saveBtn;

    // Database helper object
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        // Link XML UI elements to Java code
        nameInput = findViewById(R.id.nameInput);
        addressInput = findViewById(R.id.addressInput);
        phoneInput = findViewById(R.id.phoneInput);
        saveBtn = findViewById(R.id.saveBtn);

        // Initialize DBHelper
        dbHelper = new DBHelper(this);

        // Save Button Click Listener
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String name = nameInput.getText().toString().trim();
                String address = addressInput.getText().toString().trim();
                String phone = phoneInput.getText().toString().trim();

                // Simple validation
                if(name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(InsertActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Insert into database
                    boolean success = dbHelper.insertCustomer(name, address, phone);

                    if(success) {
                        Toast.makeText(InsertActivity.this, "Customer Added Successfully!", Toast.LENGTH_SHORT).show();
                        nameInput.setText("");
                        addressInput.setText("");
                        phoneInput.setText("");
                    } else {
                        Toast.makeText(InsertActivity.this, "Failed to Add Customer", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
