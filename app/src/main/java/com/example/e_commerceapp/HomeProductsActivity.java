package com.example.e_commerceapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.e_commerceapp.adapters.HomeAdapter;
import com.example.e_commerceapp.models.Home;

import java.util.ArrayList;

public class HomeProductsActivity extends AppCompatActivity {

    ListView listHome;

    ImageView imageHomeBack;
    ImageView ivCategories;

    TextView tvCategories;

    ArrayList<Home> homeArrayList;
    HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_products);

        // ربط عناصر الصفحة
        listHome = findViewById(R.id.listHome);
        imageHomeBack = findViewById(R.id.imageHomeBack);


        homeArrayList = new ArrayList<>();

        homeArrayList.add(
                new Home(
                        1,
                        "Modern Fabric Sofa",
                        5,
                        499f,
                        R.drawable.modern_sofa
                )
        );


        homeArrayList.add(
                new Home(
                        2,
                        "Wooden Dining Table Set",
                        5,
                        299f,
                        R.drawable.dining_table_set
                )
        );


        homeArrayList.add(
                new Home(
                        3,
                        "Ceramic Table Lamp",
                        4,
                        39f,
                        R.drawable.ceramic_lamp
                )
        );

        homeArrayList.add(
                new Home(
                        4,
                        "Soft Area Rug",
                        4,
                        89f,
                        R.drawable.area_rug
                )
        );

        homeArrayList.add(
                new Home(
                        5,
                        "Comfort Bed",
                        5,
                        399f,
                        R.drawable.bed
                )
        );

        homeArrayList.add(
                new Home(
                        6,
                        "Modern Curtains",
                        4,
                        55f,
                        R.drawable.curtains
                )
        );

        homeArrayList.add(
                new Home(
                        7,
                        "Modern Fridge",
                        5,
                        649f,
                        R.drawable.fridge
                )
        );

        // إنشاء Adapter
        homeAdapter = new HomeAdapter(
                HomeProductsActivity.this,
                homeArrayList
        );


        listHome.setAdapter(homeAdapter);

        imageHomeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }
}