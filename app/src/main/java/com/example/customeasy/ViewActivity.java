package com.example.customeasy;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {

    // UI elements
    EditText idInput;
    Button searchBtn;
    TextView resultText;

    // Database object
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        // Initialize views
        idInput = findViewById(R.id.idInput);
        searchBtn = findViewById(R.id.searchBtn);
        resultText = findViewById(R.id.resultText);

        // Initialize DB
        dbHelper = new DBHelper(this);

        // Button click listener
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String idText = idInput.getText().toString().trim();

                // Validate input
                if (idText.isEmpty()) {
                    Toast.makeText(ViewActivity.this, "Please enter a valid ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                int id = Integer.parseInt(idText);

                // Fetch record
                Cursor cursor = dbHelper.getCustomer(id);

                if (cursor.getCount() == 0) {
                    resultText.setText("No customer found with ID: " + id);
                    return;
                }

                // Move to first record
                cursor.moveToFirst();

                // Retrieve data from cursor
                String name = cursor.getString(1);
                String address = cursor.getString(2);
                String phone = cursor.getString(3);

                // Display customer details
                String result = "ID: " + id + "\n"
                        + "Name: " + name + "\n"
                        + "Address: " + address + "\n"
                        + "Phone: " + phone;

                resultText.setText(result);
            }
        });
    }
}
