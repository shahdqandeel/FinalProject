package com.example.e_commerceapp.activities;



import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.R;
import com.example.e_commerceapp.adapters.MyOrderAdapter;
import com.example.e_commerceapp.models.MyOrder;

import java.util.ArrayList;

public class MyOrdersActivity extends AppCompatActivity {

    private ListView listViewOrders;
    private ArrayList<MyOrder> orderList;
    private MyOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        ImageView btnBack = findViewById(R.id.btnBack);
        listViewOrders = findViewById(R.id.listViewOrders);

        orderList = new ArrayList<>();

        // زر الرجوع
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // تجهيز بيانات الطلبات
        setupOrderData();

        // ربط الـ Adapter بالـ ListView
        adapter = new MyOrderAdapter(MyOrdersActivity.this, orderList);
        listViewOrders.setAdapter(adapter);
    }

    private void setupOrderData() {

        orderList.add(new MyOrder(
                1,
                "NM123456",
                "May 20, 2025",
                "iPhone 15",
                "256GB, Blue",
                999.00,
                1099.00,
                1,
                "Delivered",
                R.drawable.ic_orders
        ));

        orderList.add(new MyOrder(
                2,
                "NM123455",
                "May 15, 2025",
                "Sony Headphones",
                "WH-1000XM5, Black",
                199.00,
                219.00,
                1,
                "Shipped",
                R.drawable.ic_orders
        ));

        orderList.add(new MyOrder(
                3,
                "NM123454",
                "May 10, 2025",
                "Smart Watch",
                "Black",
                149.00,
                159.00,
                1,
                "Processing",
                R.drawable.ic_orders
        ));
    }
}