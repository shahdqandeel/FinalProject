package com.example.e_commerceapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.R;
import com.example.e_commerceapp.utils.SessionManager;

public class ProfileScreen extends AppCompatActivity {

    private SessionManager sessionManager;
    private TextView tvUserName, tvUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        sessionManager = new SessionManager(getApplicationContext());

        tvUserName = findViewById(R.id.tvUserName);
        tvUserEmail = findViewById(R.id.tvUserEmail);

        LinearLayout itemOrders = findViewById(R.id.itemOrders);
        LinearLayout itemAddresses = findViewById(R.id.itemAddresses);
        LinearLayout itemPayment = findViewById(R.id.itemPayment);
        LinearLayout itemSettings = findViewById(R.id.itemSettings);
        LinearLayout itemHelp = findViewById(R.id.itemHelp);
        LinearLayout itemLogout = findViewById(R.id.itemLogout);

        displayUserData();

        itemOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileScreen.this, MyOrdersActivity.class);
                startActivity(intent);
            }
        });

        itemAddresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileScreen.this, MyAddressesActivity.class);
                startActivity(intent);
            }
        });

        itemPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileScreen.this, PaymentMethodsActivity.class);
                startActivity(intent);
            }
        });

        itemSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileScreen.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        itemHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileScreen.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        itemLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });
    }

    private void displayUserData() {
        tvUserName.setText(sessionManager.getFullName());
        tvUserEmail.setText(sessionManager.getEmail());
    }

    private void showLogoutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sessionManager.logout();
                        Intent intent = new Intent(ProfileScreen.this, Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayUserData();
    }
}