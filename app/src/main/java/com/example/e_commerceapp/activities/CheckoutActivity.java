package com.example.e_commerceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.R;
import com.example.e_commerceapp.models.Address;
import com.example.e_commerceapp.models.MyOrder;
import com.example.e_commerceapp.utils.AddressManager;
import com.example.e_commerceapp.utils.OrderManager;
import com.example.e_commerceapp.utils.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

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
    int itemCount = 1;
    String selectedPaymentMethod = "Cash on Delivery";

    SessionManager sessionManager;
    AddressManager addressManager;
    OrderManager orderManager;
    Address currentAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        sessionManager = new SessionManager(this);
        addressManager = new AddressManager(this);
        orderManager = new OrderManager(this);

        initViews();
        receivePricesAndCalculate();
        loadUserAndAddressData();
        setupClickListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.iv_back);
        tvChangeAddress = findViewById(R.id.tv_change_address);
        tvName = findViewById(R.id.tv_user_name);
        tvAddress = findViewById(R.id.tv_address_details);
        tvPhone = findViewById(R.id.tv_phone_number);

        imgCash = findViewById(R.id.iv_cash);
        imgCard = findViewById(R.id.iv_card);
        imgPaypal = findViewById(R.id.iv_paypal);

        layoutCash = findViewById(R.id.payment_cash);
        layoutCard = findViewById(R.id.payment_credit_card);
        layoutPaypal = findViewById(R.id.payment_paypal);

        // ملاحظة: هالـ IDs مطابقة تماماً لملف XML (tv_summary_subtotal وليس tv_subtotal)
        tvSubtotal = findViewById(R.id.tv_summary_subtotal);
        tvShipping = findViewById(R.id.tv_summary_shipping);
        tvTotal = findViewById(R.id.tv_summary_total);

        btnPlaceOrder = findViewById(R.id.btn_place_order);
    }

    // استقبال الأسعار الحقيقية القادمة من صفحة Cart أو Product Details
    private void receivePricesAndCalculate() {
        Intent intent = getIntent();
        if (intent != null) {
            subtotal = intent.getDoubleExtra("subtotal_price", 0.0);
            shipping = intent.getDoubleExtra("shipping_price", 10.00);
            itemCount = intent.getIntExtra("item_count", 1);
        }

        total = subtotal + shipping;

        tvSubtotal.setText(String.format("$%.2f", subtotal));
        tvShipping.setText(String.format("$%.2f", shipping));
        tvTotal.setText(String.format("$%.2f", total));
    }

    // عرض بيانات المستخدم والعنوان الحقيقية بدل النصوص الثابتة
    private void loadUserAndAddressData() {
        currentAddress = addressManager.getDefaultAddress();

        if (currentAddress != null) {
            tvName.setText(currentAddress.getFullName());
            tvAddress.setText(currentAddress.getStreetAddress() + ", " + currentAddress.getCity());
            tvPhone.setText(currentAddress.getPhoneNumber());
        } else {
            String userName = sessionManager.getFullName();
            tvName.setText(userName != null && !userName.isEmpty() ? userName : "Guest User");
            tvAddress.setText("No address saved yet");
            tvPhone.setText("Add a phone number");
        }
    }

    private void setupClickListeners() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // زر Change: ينقل المستخدم فعلياً لصفحة My Addresses لإدارة عناوينه الحقيقية
        tvChangeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckoutActivity.this, MyAddressesActivity.class);
                startActivity(intent);
            }
        });

        layoutCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPaymentMethod("cash");
            }
        });

        layoutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPaymentMethod("card");
            }
        });

        layoutPaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPaymentMethod("paypal");
            }
        });

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentAddress == null) {
                    Toast.makeText(CheckoutActivity.this, "Please add a shipping address first", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (subtotal <= 0) {
                    Toast.makeText(CheckoutActivity.this, "Your cart is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                // إنشاء الطلب الفعلي وحفظه
                createAndSaveOrder();

                // الانتقال الفعلي لصفحة نجاح الطلب
                Intent intent = new Intent(CheckoutActivity.this, OrderSuccessActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void selectPaymentMethod(String method) {
        imgCash.setImageResource(R.drawable.ic_radio_off);
        imgCard.setImageResource(R.drawable.ic_radio_off);
        imgPaypal.setImageResource(R.drawable.ic_radio_off);

        switch (method) {
            case "cash":
                imgCash.setImageResource(R.drawable.ic_radio_on);
                selectedPaymentMethod = "Cash on Delivery";
                break;

            case "card":
                imgCard.setImageResource(R.drawable.ic_radio_on);
                selectedPaymentMethod = "Credit Card";
                break;

            case "paypal":
                imgPaypal.setImageResource(R.drawable.ic_radio_on);
                selectedPaymentMethod = "PayPal";
                break;
        }
    }

    // إنشاء كائن MyOrder حقيقي بناءً على بيانات الطلب الفعلية وحفظه عبر OrderManager
    private void createAndSaveOrder() {
        int newId = generateId();
        String orderId = generateOrderId();
        String orderDate = getCurrentDate();

        MyOrder newOrder = new MyOrder(
                newId,
                orderId,
                orderDate,
                itemCount > 1 ? "Multiple Items" : "1 Item",
                selectedPaymentMethod,
                subtotal,
                total,
                itemCount,
                "Processing",
                R.drawable.ic_orders
        );

        orderManager.addOrder(newOrder);
    }

    private int generateId() {
        return (int) System.currentTimeMillis();
    }

    private String generateOrderId() {
        Random random = new Random();
        int randomNum = 100000 + random.nextInt(900000);
        return "NM" + randomNum;
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        return sdf.format(new Date());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // تحديث بيانات العنوان تلقائياً لو المستخدم رجع من صفحة My Addresses بعد تعديل/إضافة عنوان
        loadUserAndAddressData();
    }
}