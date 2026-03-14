package com.example.stocdex.ui.operations;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.stocdex.R;
import com.stocdex.data.ThemeUtils;

public class DeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
    }
}