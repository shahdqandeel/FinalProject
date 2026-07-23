package com.example.e_commerceapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

import com.example.e_commerceapp.adapters.CartAdapter;
import com.example.e_commerceapp.adapters.FavoriteAdapter;
import com.example.e_commerceapp.models.CartItem;
import com.example.e_commerceapp.models.Product;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    TextView tvCartTitle,tvSubtotalPrice,tvShippingPrice,tvTotalPrice;
    ListView lv;
    ArrayList<CartItem> cartList;
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        lv = findViewById(R.id.lv_cart_item);
        tvCartTitle = findViewById(R.id.tv_toolbar_title);
        tvSubtotalPrice = findViewById(R.id.tv_subtotal_val);
        tvShippingPrice = findViewById(R.id.tv_shipping_val);
        tvTotalPrice = findViewById(R.id.tv_total_val);
        cartList = new ArrayList<>();
        cartList.add(new CartItem(1, "Hoodie", "$45.00", R.drawable.img_hoodie, 2));
        cartList.add(new CartItem(2, "iPhone 15", "$999.00", R.drawable.img_iphone, 1));
        cartList.add(new CartItem(3, "Elegant Fabric Sofa", "$39.00", R.drawable.img_modern_fabric_sofa, 1));
        cartList.add(new CartItem(4, "Glow Skin Care Set", "$29.00", R.drawable.img_glow_skin_care_set, 1));
        adapter = new CartAdapter(CartActivity.this, cartList, new CartAdapter.OnCartChangedListener() {
            public void onCartChanged() {
                updateOrderSummary();
            }
        });
        lv.setAdapter(adapter);
        updateOrderSummary();
    }
        private void updateOrderSummary(){
            double subtotal=0.0;
            double shipping=10.0;
            for(CartItem item:cartList){
                String priceStr = item.getPrice().replace("$","").trim();
                double price = Double.parseDouble(priceStr);
                subtotal += (price * item.getQuantity());
            }
            if(cartList.isEmpty()){
                shipping = 0.0;
            }
            double total = subtotal + shipping;
            tvCartTitle.setText("Your Cart("+cartList.size()+")");
            tvSubtotalPrice.setText(String.format("$%.2f",subtotal));
            tvShippingPrice.setText(String.format("$%.2f",shipping));
            tvTotalPrice.setText(String.format("$%.2f",total));

    }
}