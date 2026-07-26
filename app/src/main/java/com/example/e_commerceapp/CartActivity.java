package com.example.e_commerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.adapters.CartAdapter;
import com.example.e_commerceapp.models.CartItem;
import com.example.e_commerceapp.models.CartManager;

import java.util.ArrayList;


public class CartActivity extends AppCompatActivity {

    ListView lvCartItems;
    CartAdapter cartAdapter;
    TextView tvCartHeaderTitle, tvSubtotal, tvShipping, tvTotal;
    Button btnCheckout;
    ImageView btnBack;

    double subtotalPrice = 0.0;
    double shippingFee = 10.00; // قيمة الشحن الثابتة

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initViews();
        setupListView();
        updateCartSummary();
        setupClickListeners();
    }
    private void setupClickListeners() {
        // زر الرجوع
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }
        // زر الـ Checkout للانتقال لصفحة الدفع
        if (btnCheckout != null) {
            btnCheckout.setOnClickListener(v -> {
                if (CartManager.getCartList().isEmpty()) {
                    Toast.makeText(CartActivity.this, "Your cart is empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
                intent.putExtra("subtotal_price", subtotalPrice);
                intent.putExtra("shipping_price", shippingFee);
                startActivity(intent);
            });
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        refreshCartData();  // إعادة تحديث القيم عند العودة للشاشة
    }

    private void initViews() {
        lvCartItems = findViewById(R.id.lv_cart_item);
        tvCartHeaderTitle = findViewById(R.id.tv_toolbar_title);
        tvSubtotal = findViewById(R.id.tv_subtotal_val);
        tvShipping = findViewById(R.id.tv_shipping_val);
        tvTotal = findViewById(R.id.tv_total_val);
        btnCheckout = findViewById(R.id.btn_checkout);
        btnBack = findViewById(R.id.iv_back);
    }

    private void setupListView() {
        ArrayList<CartItem> cartItems = CartManager.getCartList();

        cartAdapter = new CartAdapter(this, cartItems, new CartAdapter.OnCartChangeListener() {
            @Override
            public void onItemChanged() {
                updateCartSummary();
            }
        });
        if (lvCartItems != null) {
            lvCartItems.setAdapter(cartAdapter);
        }
    }

    // دالة تحديث حسابات الأسعار على الشاشة
    public void updateCartSummary() {

        ArrayList<CartItem> items = CartManager.getCartList();

        int totalItemCount = 0;
        subtotalPrice = 0.0;

        // حساب عدد العناصر الكلي والمجموع الفرعي
        for (CartItem item : items) {
            totalItemCount += item.getQuantity();
            double itemPrice = parsePriceToDouble(item.getPrice());
            subtotalPrice += (itemPrice * item.getQuantity());
        }

        // تحديث عنوان الشاشة بالعدد الفعلي
        if (tvCartHeaderTitle != null) {
            tvCartHeaderTitle.setText("Your Cart (" + totalItemCount + ")");
        }

        // تحديث الأسعار
        if (subtotalPrice > 0) {
            double totalPrice = subtotalPrice + shippingFee;

            if (tvSubtotal != null) tvSubtotal.setText(String.format("$%.2f", subtotalPrice));
            if (tvShipping != null) tvShipping.setText(String.format("$%.2f", shippingFee));
            if (tvTotal != null) tvTotal.setText(String.format("$%.2f", totalPrice));
        } else {
            if (tvSubtotal != null) tvSubtotal.setText("$0.00");
            if (tvShipping != null) tvShipping.setText("$0.00");
            if (tvTotal != null) tvTotal.setText("$0.00");
        }
    }
    private void refreshCartData() {
        if (cartAdapter != null) {
            cartAdapter.notifyDataSetChanged();
        }
        updateCartSummary();
    }
    private double parsePriceToDouble(String priceStr) {
        try {
            if (priceStr != null) {
                String cleanPrice = priceStr.replaceAll("[^0-9.]", "").trim();
                if (!cleanPrice.isEmpty()) {
                    return Double.parseDouble(cleanPrice);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}