package com.example.customeasy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteActivity extends AppCompatActivity {

    // UI elements
    EditText deleteIdInput;
    Button deleteBtn;

    // Database helper
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        // Link UI elements
        deleteIdInput = findViewById(R.id.deleteIdInput);
        deleteBtn = findViewById(R.id.deleteBtn);

        // Initialize DBHelper
        dbHelper = new DBHelper(this);

        // Delete button click event
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idText = deleteIdInput.getText().toString().trim();

                if (idText.isEmpty()) {
                    Toast.makeText(DeleteActivity.this, "Please enter a Customer ID !", Toast.LENGTH_SHORT).show();
                    return;
                }

                int id = Integer.parseInt(idText);
                boolean deleted = dbHelper.deleteCustomer(id);

                if (deleted) {
                    Toast.makeText(DeleteActivity.this, "Customer deleted successfully !!", Toast.LENGTH_SHORT).show();
                    deleteIdInput.setText("");
                } else {
                    Toast.makeText(DeleteActivity.this, "No customer found with that ID...!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
