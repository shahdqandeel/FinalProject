package com.example.e_commerceapp.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.R;

public class HelpActivity extends AppCompatActivity {

    ImageView btnBack;
    LinearLayout layoutEmail, layoutPhone, layoutWhatsApp, layoutLocation;
    Button btnSendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.iv_back);
        layoutEmail = findViewById(R.id.layout_email);
        layoutPhone = findViewById(R.id.layout_phone);
        layoutWhatsApp = findViewById(R.id.layout_whatsapp);
        layoutLocation = findViewById(R.id.layout_location);
        btnSendMessage = findViewById(R.id.btn_send_message);
    }

    private void setupClickListeners() {
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        if (layoutEmail != null) {
            layoutEmail.setOnClickListener(v ->
                    Toast.makeText(this, "Email: support@novamart.com", Toast.LENGTH_SHORT).show()
            );
        }

        if (layoutPhone != null) {
            layoutPhone.setOnClickListener(v ->
                    Toast.makeText(this, "Phone: +970 59 123 4567", Toast.LENGTH_SHORT).show()
            );
        }

        if (layoutWhatsApp != null) {
            layoutWhatsApp.setOnClickListener(v ->
                    Toast.makeText(this, "WhatsApp: +970 59 123 4567", Toast.LENGTH_SHORT).show()
            );
        }

        if (layoutLocation != null) {
            layoutLocation.setOnClickListener(v ->
                    Toast.makeText(this, "Location: Gaza City, Palestine", Toast.LENGTH_SHORT).show()
            );
        }

        if (btnSendMessage != null) {
            btnSendMessage.setOnClickListener(v ->
                    Toast.makeText(this, "The Message has been sent successfully, we will contact you soon", Toast.LENGTH_SHORT).show()
            );
        }
    }
}