package com.example.stocdex.ui.settings;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.stocdex.R;
import com.stocdex.data.ThemeMode;
import com.stocdex.data.ThemePrefs;
import com.stocdex.data.ThemeUtils;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        RadioGroup radioGroup = findViewById(R.id.radioGroupTheme);
        RadioButton radioLight = findViewById(R.id.radioLight);
        RadioButton radioDark = findViewById(R.id.radioDark);
        RadioButton radioSystem = findViewById(R.id.radioSystem);

        ThemeMode current = ThemePrefs.getThemeMode(this);
        if (current == ThemeMode.LIGHT) radioLight.setChecked(true);
        else if (current == ThemeMode.DARK) radioDark.setChecked(true);
        else radioSystem.setChecked(true);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            ThemeMode newMode;
            if (checkedId == R.id.radioLight) newMode = ThemeMode.LIGHT;
            else if (checkedId == R.id.radioDark) newMode = ThemeMode.DARK;
            else newMode = ThemeMode.SYSTEM;
            ThemePrefs.setThemeMode(this, newMode);
            ThemeUtils.applyTheme(this);
            recreate();
        });
    }
}