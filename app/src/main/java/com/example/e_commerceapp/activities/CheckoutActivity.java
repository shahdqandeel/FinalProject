package com.example.e_commerceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.R;

public class CheckoutActivity extends AppCompatActivity {

    ImageView btnBack;
    TextView tvChangeAddress;
    TextView tvName, tvAddress, tvPhone;
    ImageView imgCash, imgCard, imgPaypal;
    LinearLayout layoutCash, layoutCard, layoutPaypal;
    TextView tvSubtotal, tvShipping, tvTotal;
    Button btnPlaceOrder;

    double subtotal = 0.0;
    double shipping = 10.00;
    double total = 0.0;
    String selectedPaymentMethod = "Cash on Delivery"; // الافتراضي

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        // ربط عناصر الواجهة
        initViews();

        // استقبال الأسعار القادمة من السلة أو صفحة التفاصيل
        receivePricesAndCalculate();

        // تفعيل أزرار التفاعل
        setupClickListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.iv_back);
        tvChangeAddress = findViewById(R.id.tv_change_address);
        tvName = findViewById(R.id.tv_user_name);
        tvAddress = findViewById(R.id.tv_address_details);
        tvPhone = findViewById(R.id.tv_phone_number);
        // ربط صور الـ Radio
        imgCash = findViewById(R.id.iv_cash);
        imgCard = findViewById(R.id.iv_card);
        imgPaypal = findViewById(R.id.iv_paypal);
        // الحاويات الخاصة بكل طريقة دفع لتسهيل الضغط
        layoutCash = findViewById(R.id.payment_cash);
        layoutCard = findViewById(R.id.payment_credit_card);
        layoutPaypal = findViewById(R.id.payment_paypal);
        tvSubtotal = findViewById(R.id.tv_summary_subtotal);
        tvShipping = findViewById(R.id.tv_summary_shipping);
        tvTotal = findViewById(R.id.tv_summary_total);
        btnPlaceOrder = findViewById(R.id.btn_place_order);
    }

    private void receivePricesAndCalculate() {
        Intent intent = getIntent();
        if (intent != null) {
            subtotal = intent.getDoubleExtra("subtotal_price", 0.0);
            shipping = intent.getDoubleExtra("shipping_price", 10.00);
        }

        total = subtotal + shipping;

        tvSubtotal.setText(String.format("$%.2f", subtotal));
        tvShipping.setText(String.format("$%.2f", shipping));
        tvTotal.setText(String.format("$%.2f", total));
    }

    private void setupClickListeners() {
        // زر الرجوع
        btnBack.setOnClickListener(v -> finish());

        // 1. عند الضغط على كلمة Change لتغيير عنوان الزبون
        tvChangeAddress.setOnClickListener(v -> showChangeAddressDialog());

        // 2. التحكم في اختيار طريقة الدفع وتبديل الصور (radio_on / radio_off)
        if (layoutCash != null) {
            layoutCash.setOnClickListener(v -> selectPaymentMethod("cash"));
        } else if (imgCash != null) {
            imgCash.setOnClickListener(v -> selectPaymentMethod("cash"));
        }

        if (layoutCard != null) {
            layoutCard.setOnClickListener(v -> selectPaymentMethod("card"));
        } else if (imgCard != null) {
            imgCard.setOnClickListener(v -> selectPaymentMethod("card"));
        }

        if (layoutPaypal != null) {
            layoutPaypal.setOnClickListener(v -> selectPaymentMethod("paypal"));
        } else if (imgPaypal != null) {
            imgPaypal.setOnClickListener(v -> selectPaymentMethod("paypal"));
        }

        // 3. زر تأكيد الطلب والانتقال إلى شاشة النجاح الأخيرة
        btnPlaceOrder.setOnClickListener(v -> {
            Intent intent = new Intent(CheckoutActivity.this, OrderSuccessActivity.class);
            startActivity(intent);
            finish();
        });
    }

    // --- دالة تبديل صور الـ Radio Button ---
    private void selectPaymentMethod(String method) {
        // تعيين الصور الافتراضية للجميع إلى radio_off
        if (imgCash != null) imgCash.setImageResource(R.drawable.ic_radio_off);
        if (imgCard != null) imgCard.setImageResource(R.drawable.ic_radio_off);
        if (imgPaypal != null) imgPaypal.setImageResource(R.drawable.ic_radio_off);

        // تفعيل الخيار المحدد وتحويل صورته إلى radio_on
        switch (method) {
            case "cash":
                if (imgCash != null) imgCash.setImageResource(R.drawable.ic_radio_on);
                selectedPaymentMethod = "Cash on Delivery";
                break;

            case "card":
                if (imgCard != null) imgCard.setImageResource(R.drawable.ic_radio_on);
                selectedPaymentMethod = "Credit Card";
                break;

            case "paypal":
                if (imgPaypal != null) imgPaypal.setImageResource(R.drawable.ic_radio_on);
                selectedPaymentMethod = "PayPal";
                break;
        }
    }

    // --- دالة إظهار نافذة تعديل العنوان عند الضغط على Change ---
    private void showChangeAddressDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Shipping Address");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 20, 50, 20);

        final EditText etName = new EditText(this);
        etName.setHint("Customer Name");
        etName.setText(tvName.getText().toString());
        layout.addView(etName);

        final EditText etAddress = new EditText(this);
        etAddress.setHint("Full Address");
        etAddress.setText(tvAddress.getText().toString());
        layout.addView(etAddress);

        final EditText etPhone = new EditText(this);
        etPhone.setHint("Phone Number");
        etPhone.setText(tvPhone.getText().toString());
        layout.addView(etPhone);

        builder.setView(layout);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String newName = etName.getText().toString().trim();
            String newAddress = etAddress.getText().toString().trim();
            String newPhone = etPhone.getText().toString().trim();

            if (!newName.isEmpty()) tvName.setText(newName);
            if (!newAddress.isEmpty()) tvAddress.setText(newAddress);
            if (!newPhone.isEmpty()) tvPhone.setText(newPhone);

            Toast.makeText(CheckoutActivity.this, "Address updated!", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }
}