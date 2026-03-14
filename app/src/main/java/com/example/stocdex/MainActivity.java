package com.example.stocdex;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.stocdex.data.InventoryRepository;
import com.stocdex.data.ThemeUtils;
import com.stocdex.ui.auth.LoginActivity;
import com.stocdex.ui.dashboard.DashboardActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean loggedIn = InventoryRepository.getInstance().isLoggedIn();
        Intent intent = new Intent(this, loggedIn ? DashboardActivity.class : LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}