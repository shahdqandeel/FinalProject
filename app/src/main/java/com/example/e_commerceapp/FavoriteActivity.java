package com.example.e_commerceapp;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.e_commerceapp.adapters.FavoriteAdapter;
import com.example.e_commerceapp.models.Product;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Product> productArrayList;
    FavoriteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favorite);
        lv=findViewById(R.id.lv_favorites);
        productArrayList=new ArrayList<>();
        productArrayList.add(new Product(1,"Hoodie","$45.00",R.drawable.img_hoodie,4.7f,"85",
                "Comfortable and stylish hoodie made from premium cotton blend fabric. Prefect for everyday wear. Available in multiple colors.",
                "fashion",new String[]{"#222222","#E6D7C3","#CBD5E1"},
                new String[]{"S","M","L"},null));
        productArrayList.add(new Product(2,"iPhone 15","$999.00",R.drawable.img_iphone,5.0f,"120",
                "The iPhone 15 features a 6.1 inch Super Retina XDR display, A16 Bionic chip, and advanced dual-camera system. Experience incredible performance and all-day battary life.",
                "electronics",new String[]{"#222222","#E6D7C3"},null,
                new String[]{"128GB","256GB","512GB"}));
        productArrayList.add(new Product(3,"Elegant Fabric Sofa","$39.00",R.drawable.img_modern_fabric_sofa,3.9f,"56",
                "Stylish and comfortable 3_seater sofa perfect for any living room. Made with high_quality fabric and sturdy wooden legs.",
                "home",new String[]{"#E6D7C3","#8F9779","#A1A8B8"},
                null,
                null));

        adapter=new FavoriteAdapter(FavoriteActivity.this,productArrayList);
        lv.setAdapter(adapter);
    }
}