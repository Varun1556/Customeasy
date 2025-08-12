package com.example.customeasy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText regUsernameInput, regPasswordInput, regConfirmPasswordInput;
    Button registerBtn, goToLoginBtn;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regUsernameInput = findViewById(R.id.regUsernameInput);
        regPasswordInput = findViewById(R.id.regPasswordInput);
        regConfirmPasswordInput = findViewById(R.id.regConfirmPasswordInput);
        registerBtn = findViewById(R.id.registerBtn);
        goToLoginBtn = findViewById(R.id.goToLoginBtn);
        dbHelper = new DBHelper(this);

        registerBtn.setOnClickListener(view -> {
            String username = regUsernameInput.getText().toString().trim();
            String password = regPasswordInput.getText().toString().trim();
            String confirmPassword = regConfirmPasswordInput.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean success = dbHelper.registerUser(username, password);
            if (success) {
                Toast.makeText(this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
            }
        });

        goToLoginBtn.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }
}
