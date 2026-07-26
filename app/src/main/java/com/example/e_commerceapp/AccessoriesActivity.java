package com.example.e_commerceapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.e_commerceapp.adapters.AccessoriesAdapter;
import com.example.e_commerceapp.models.Accessories;

import java.util.ArrayList;

public class AccessoriesActivity extends AppCompatActivity {

    ListView listAccessories;

    ImageView imageAccessoriesBack;
    ImageView ivCategories;

    TextView tvCategories;

    ArrayList<Accessories> accessoriesArrayList;
    AccessoriesAdapter accessoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_accessories);

        // ربط عناصر الصفحة
        listAccessories = findViewById(R.id.listAccessories);
        imageAccessoriesBack = findViewById(R.id.imageAccessoriesBack);
        accessoriesArrayList = new ArrayList<>();


        accessoriesArrayList.add(
                new Accessories(
                        1,
                        "Leather Bag",
                        5,
                        65f,
                        R.drawable.img_elegant_shoulder_bag
                )
        );
        accessoriesArrayList.add(
                new Accessories(
                        2,
                        "Rose Gold Watch",
                        5,
                        120f,
                        R.drawable.rose_gold_watch
                )
        );


        accessoriesArrayList.add(
                new Accessories(
                        3,
                        "Sunglasses",
                        4,
                        35f,
                        R.drawable.product_sunglasses
                )
        );

        accessoriesArrayList.add(
                new Accessories(
                        4,
                        "Necklace",
                        5,
                        55f,
                        R.drawable.necklace
                )
        );


        accessoriesArrayList.add(
                new Accessories(
                        5,
                        "Bridal Tiara",
                        5,
                        80f,
                        R.drawable.bridal_tiara
                )
        );

        accessoriesArrayList.add(
                new Accessories(
                        6,
                        "Scrunchie",
                        4,
                        8f,
                        R.drawable.scrunchie
                )
        );
        accessoriesArrayList.add(
                new Accessories(
                        7,
                        "Cap",
                        4,
                        20f,
                        R.drawable.cap
                )
        );

        accessoriesAdapter = new AccessoriesAdapter(
                AccessoriesActivity.this,
                accessoriesArrayList
        );


        listAccessories.setAdapter(accessoriesAdapter);

        imageAccessoriesBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}