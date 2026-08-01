package com.example.e_commerceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.R;
import com.example.e_commerceapp.adapters.MyOrderAdapter;
import com.example.e_commerceapp.models.MyOrder;
import com.example.e_commerceapp.utils.OrderManager;

import java.util.ArrayList;

public class MyOrdersActivity extends AppCompatActivity {

    private ListView listViewOrders;
    private ArrayList<MyOrder> orderList;
    private MyOrderAdapter adapter;
    private OrderManager orderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        orderManager = new OrderManager(this);

        ImageView btnBack = findViewById(R.id.btnBack);
        listViewOrders = findViewById(R.id.listViewOrders);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadOrders();
    }

    // جلب الطلبات الحقيقية المخزنة فعلياً وعرضها بالقائمة
    private void loadOrders() {
        orderList = orderManager.getAllOrders();

        if (orderList.isEmpty()) {
            Toast.makeText(this, "No orders yet. Start shopping!", Toast.LENGTH_SHORT).show();
        }

        adapter = new MyOrderAdapter(this, orderList);
        listViewOrders.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // إعادة تحميل الطلبات في كل مرة نرجع فيها لهذه الصفحة
        // (مهم جداً لو المستخدم عمل طلب جديد من Checkout ورجع هون)
        loadOrders();
    }
}