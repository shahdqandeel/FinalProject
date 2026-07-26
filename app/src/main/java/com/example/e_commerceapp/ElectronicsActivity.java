package com.example.e_commerceapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.e_commerceapp.adapters.ElectronicsAdapter;
import com.example.e_commerceapp.models.Electronics;

import java.util.ArrayList;

public class ElectronicsActivity extends AppCompatActivity {

    ListView listElectronics;

    ImageView imageElectronicsBack;
    ImageView ivCategories;

    ArrayList<Electronics> electronicsArrayList;
    ElectronicsAdapter electronicsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_electronics);

        // ربط عناصر الواجهة
        listElectronics = findViewById(R.id.listElectronics);
        imageElectronicsBack = findViewById(R.id.imageElectronicsBack);


        electronicsArrayList = new ArrayList<>();

        electronicsArrayList.add(
                new Electronics(
                        1,
                        "iPhone 15",
                        5,
                        999f,
                        R.drawable.img_iphone
                )
        );

        electronicsArrayList.add(
                new Electronics(
                        2,
                        "Samsung Galaxy S24",
                        5,
                        899f,
                        R.drawable.galaxy_s24
                )
        );

        electronicsArrayList.add(
                new Electronics(
                        3,
                        "Sony Headphones",
                        4,
                        199f,
                        R.drawable.product_headphone
                )
        );

        electronicsArrayList.add(
                new Electronics(
                        4,
                        "Smart Watch",
                        4,
                        149f,
                        R.drawable.smart_watch
                )
        );

        electronicsArrayList.add(
                new Electronics(
                        5,
                        "Dell Laptop",
                        5,
                        799f,
                        R.drawable.dell_laptop
                )
        );

        electronicsArrayList.add(
                new Electronics(
                        6,
                        "Wireless Mouse",
                        4,
                        25f,
                        R.drawable.wireless_mouse
                )
        );

        electronicsArrayList.add(
                new Electronics(
                        7,
                        "Keyboard",
                        4,
                        45f,
                        R.drawable.keyboard
                )
        );

        electronicsArrayList.add(
                new Electronics(
                        8,
                        "Power Bank",
                        4,
                        35f,
                        R.drawable.power_bank
                )
        );

        electronicsAdapter = new ElectronicsAdapter(
                ElectronicsActivity.this,
                electronicsArrayList
        );


        listElectronics.setAdapter(electronicsAdapter);

        imageElectronicsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }
}