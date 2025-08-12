package com.example.customeasy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoutBtn = findViewById(R.id.logoutBtn);

        logoutBtn.setOnClickListener(view -> {
            SharedPreferences prefs = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear(); // Clear session
            editor.apply();

            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);
        if (!isLoggedIn) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    public void goToInsert(View view) {
        startActivity(new Intent(this, InsertActivity.class));
    }

    public void goToView(View view) {
        startActivity(new Intent(this, ViewActivity.class));
    }

    public void goToViewAll(View view) {
        startActivity(new Intent(this, ViewAllActivity.class));
    }

    public void goToUpdate(View view) {
        startActivity(new Intent(this, UpdateActivity.class));
    }

    public void goToDelete(View view) {
        startActivity(new Intent(this, DeleteActivity.class));
    }
}
