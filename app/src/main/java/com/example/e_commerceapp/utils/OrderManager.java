package com.example.e_commerceapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.e_commerceapp.models.MyOrder;

import java.util.ArrayList;

public class OrderManager {

    private static final String PREF_NAME = "NovaMartOrders";
    private static final String KEY_ORDER_COUNT = "orderCount";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public OrderManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    // إضافة طلب جديد (بيتحط بآخر القائمة، وبنعرضه بالمقلوب لاحقاً حتى يبين أول واحد)
    public void addOrder(MyOrder order) {
        int count = prefs.getInt(KEY_ORDER_COUNT, 0);

        editor.putInt("order_" + count + "_id", order.getId());
        editor.putString("order_" + count + "_orderId", order.getOrderId());
        editor.putString("order_" + count + "_date", order.getOrderDate());
        editor.putString("order_" + count + "_productName", order.getProductName());
        editor.putString("order_" + count + "_productVariant", order.getProductVariant());
        editor.putFloat("order_" + count + "_price", (float) order.getPrice());
        editor.putFloat("order_" + count + "_totalPrice", (float) order.getTotalPrice());
        editor.putInt("order_" + count + "_itemCount", order.getItemCount());
        editor.putString("order_" + count + "_status", order.getStatus());
        editor.putInt("order_" + count + "_imageResId", order.getImageResId());

        editor.putInt(KEY_ORDER_COUNT, count + 1);
        editor.apply();
    }

    // جلب كل الطلبات المحفوظة فعلياً، بترتيب عكسي (آخر طلب يظهر أولاً)
    public ArrayList<MyOrder> getAllOrders() {
        ArrayList<MyOrder> orderList = new ArrayList<>();
        int count = prefs.getInt(KEY_ORDER_COUNT, 0);

        for (int i = count - 1; i >= 0; i--) {
            int id = prefs.getInt("order_" + i + "_id", 0);
            String orderId = prefs.getString("order_" + i + "_orderId", "");
            String date = prefs.getString("order_" + i + "_date", "");
            String productName = prefs.getString("order_" + i + "_productName", "");
            String productVariant = prefs.getString("order_" + i + "_productVariant", "");
            double price = prefs.getFloat("order_" + i + "_price", 0f);
            double totalPrice = prefs.getFloat("order_" + i + "_totalPrice", 0f);
            int itemCount = prefs.getInt("order_" + i + "_itemCount", 1);
            String status = prefs.getString("order_" + i + "_status", "Processing");
            int imageResId = prefs.getInt("order_" + i + "_imageResId", 0);

            orderList.add(new MyOrder(id, orderId, date, productName, productVariant,
                    price, totalPrice, itemCount, status, imageResId));
        }

        return orderList;
    }

    public boolean hasOrders() {
        return prefs.getInt(KEY_ORDER_COUNT, 0) > 0;
    }
}
