package com.stocdex.data;

import android.content.Context;
import android.content.SharedPreferences;

public class ThemePrefs {

    private static final String PREFS_NAME = "stocdex_theme_prefs";
    private static final String KEY_MODE = "theme_mode";

    public static void setThemeMode(Context context, ThemeMode mode) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_MODE, mode.name()).apply();
    }

    public static ThemeMode getThemeMode(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String value = prefs.getString(KEY_MODE, ThemeMode.SYSTEM.name());
        try {
            return ThemeMode.valueOf(value);
        } catch (IllegalArgumentException e) {
            return ThemeMode.SYSTEM;
        }
    }
}
