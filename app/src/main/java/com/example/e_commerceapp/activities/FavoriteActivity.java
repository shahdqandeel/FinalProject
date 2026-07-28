package com.example.e_commerceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.R;
import com.example.e_commerceapp.adapters.FavoriteAdapter;
import com.example.e_commerceapp.utils.FavoriteManager;
import com.example.e_commerceapp.models.Product;


public class FavoriteActivity extends AppCompatActivity {

    ListView listViewFavorites;
    FavoriteAdapter adapter;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        btnBack = findViewById(R.id.iv_back);
        listViewFavorites = findViewById(R.id.lv_favorites);

        btnBack.setOnClickListener(v -> finish());

        // ربط الـ Adapter بالقائمة
        adapter = new FavoriteAdapter(this, FavoriteManager.getFavoriteList());
        listViewFavorites.setAdapter(adapter);

        // فتح شاشة التفاصيل عند اختيار أي عنصر من القائمة
        listViewFavorites.setOnItemClickListener((parent, view, position, id) -> {
            Product selectedItem = FavoriteManager.getFavoriteList().get(position);
            Intent intent = new Intent(FavoriteActivity.this, ProductDetailsActivity.class);
            intent.putExtra("product_id", selectedItem.getId());
            intent.putExtra("product_name", selectedItem.getName());
            intent.putExtra("product_price_str", selectedItem.getPrice());
            intent.putExtra("product_image", selectedItem.getImage());
            intent.putExtra("product_rating", selectedItem.getRating());
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // إنعاش القائمة ليعكس أي تغيير في التقييم تمت إضافته من صفحة التفاصيل
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}