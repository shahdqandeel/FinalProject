package com.example.e_commerceapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.e_commerceapp.R;

public class SettingActivity extends AppCompatActivity {
    ImageView btnBack, btnDarkMode;
    RelativeLayout layoutAccountSettings, layoutNotificationSettings, layoutPrivacySecurity, layoutLanguage, layoutCurrency, layoutAbout;
    LinearLayout btnLogout;

    private static final String PREF_NAME = "app_settings";
    private static final String KEY_NIGHT_MODE = "is_night_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initViews();
        setupClickListeners();
        setupDarkModeToggle();
    }

    private void initViews() {
        btnBack = findViewById(R.id.iv_back);
        btnDarkMode = findViewById(R.id.iv_dark_mode_switch);
        layoutAccountSettings = findViewById(R.id.item_account);
        layoutNotificationSettings = findViewById(R.id.item_notifications);
        layoutPrivacySecurity = findViewById(R.id.item_privacy);
        layoutLanguage = findViewById(R.id.item_language);
        layoutCurrency = findViewById(R.id.item_currency);
        layoutAbout = findViewById(R.id.item_about);
        btnLogout = findViewById(R.id.logout);
    }

    // --- 1. تفعيل وإيقاف الـ Dark Mode ---
    private void setupDarkModeToggle() {
        if (btnDarkMode != null) {
            SharedPreferences initPrefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            boolean currentNightMode = initPrefs.getBoolean(KEY_NIGHT_MODE, false);

            if (currentNightMode) {
                btnDarkMode.setImageResource(R.drawable.ic_radio_on);
            }else{
                btnDarkMode.setImageResource(R.drawable.ic_radio_off);
            }
            btnDarkMode.setOnClickListener(v -> {
                SharedPreferences prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
                boolean isNightMode = prefs.getBoolean(KEY_NIGHT_MODE, false);
                if (isNightMode) {
                    // التحويل للوضع الفاتح
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    prefs.edit().putBoolean(KEY_NIGHT_MODE, false).apply();
                    btnDarkMode.setImageResource(R.drawable.ic_radio_off);
                    Toast.makeText(this, "Light Mode Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    // التحويل للوضع الداكن
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    prefs.edit().putBoolean(KEY_NIGHT_MODE, true).apply();
                    btnDarkMode.setImageResource(R.drawable.ic_radio_on);
                    Toast.makeText(this, "Dark Mode Enabled", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setupClickListeners() {
        // زر الرجوع
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // --- 2. تسجيل الخروج والرجوع لصفحة LoginActivity ---
        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> showLogoutConfirmationDialog());
        }

        // باقي الخيارات مع رسائل توضيحية بسيطة
        if (layoutAccountSettings != null) {
            layoutAccountSettings.setOnClickListener(v -> Toast.makeText(this, "Account Settings", Toast.LENGTH_SHORT).show());
        }
        if (layoutNotificationSettings != null) {
            layoutNotificationSettings.setOnClickListener(v -> Toast.makeText(this, "Notification Settings", Toast.LENGTH_SHORT).show());
        }
        if (layoutPrivacySecurity != null) {
            layoutPrivacySecurity.setOnClickListener(v -> Toast.makeText(this, "Privacy & Security", Toast.LENGTH_SHORT).show());
        }
        if (layoutLanguage != null) {
            layoutLanguage.setOnClickListener(v -> Toast.makeText(this, "Language: English", Toast.LENGTH_SHORT).show());
        }
        if (layoutCurrency != null) {
            layoutCurrency.setOnClickListener(v -> Toast.makeText(this, "Currency: USD ($)", Toast.LENGTH_SHORT).show());
        }
        if (layoutAbout != null) {
            layoutAbout.setOnClickListener(v -> showAboutDialog());
        }
    }

    // --- نافذة تأكيد تسجيل الخروج ---
    private void showLogoutConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Log Out")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes", (dialog, which) -> performLogout())
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }

    // --- تنفيذ عملية تسجيل الخروج بنجاح ---
    private void performLogout() {
        // 1. مسح بيانات الجلسة والمستخدم المحفوظة (إن وجدت)
        SharedPreferences userPrefs = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        userPrefs.edit().clear().apply();

        // 2. التوجيه لصفحة تسجيل الدخول وإغلاق باقي الشاشات
        Intent intent = new Intent(SettingActivity.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // مسح سجل الشاشات السابقة
        startActivity(intent);
        finish();

        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
    }

    // --- نافذة حول التطبيق ---
    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("About NovaMart")
                .setMessage("NovaMart App\nVersion 1.0.0\n\nYour premier shopping destination.")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}