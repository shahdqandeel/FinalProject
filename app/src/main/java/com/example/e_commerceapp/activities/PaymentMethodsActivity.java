package com.example.e_commerceapp.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.R;

public class PaymentMethodsActivity extends AppCompatActivity {

    ImageView btnBack;
    TextView tvAddNew;
    RelativeLayout layoutCreditCard, layoutPayPal, layoutCashOnDelivery;

    private String selectedPaymentMethod = "Credit Card"; // افتراضي

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.iv_back);
        tvAddNew = findViewById(R.id.tv_add_new);
        layoutCreditCard = findViewById(R.id.layout_credit_card);
        layoutPayPal = findViewById(R.id.layout_paypal);
        layoutCashOnDelivery = findViewById(R.id.layout_cash_on_delivery);
    }

    private void setupClickListeners() {
        // 1. زر الرجوع
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // 2. إضافة طريقة دفع جديدة
        if (tvAddNew != null) {
            tvAddNew.setOnClickListener(v ->
                    Toast.makeText(this, "Add New Payment Method clicked", Toast.LENGTH_SHORT).show()
            );
        }

        // 3. اختيار البطاقة الائتمانية
        if (layoutCreditCard != null) {
            layoutCreditCard.setOnClickListener(v -> {
                selectedPaymentMethod = "Credit Card (Visa)";
                Toast.makeText(this, "Selected: " + selectedPaymentMethod, Toast.LENGTH_SHORT).show();
            });
        }

        // 4. اختيار PayPal
        if (layoutPayPal != null) {
            layoutPayPal.setOnClickListener(v -> {
                selectedPaymentMethod = "PayPal";
                Toast.makeText(this, "Selected: " + selectedPaymentMethod, Toast.LENGTH_SHORT).show();
            });
        }

        // 5. اختيار الدفع عند الاستلام (Cash on Delivery)
        if (layoutCashOnDelivery != null) {
            layoutCashOnDelivery.setOnClickListener(v -> {
                selectedPaymentMethod = "Cash on Delivery";
                Toast.makeText(this, "Selected: " + selectedPaymentMethod, Toast.LENGTH_SHORT).show();
            });
        }
    }
}