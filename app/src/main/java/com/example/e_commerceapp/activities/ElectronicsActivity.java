package com.example.e_commerceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.e_commerceapp.R;
import com.example.e_commerceapp.adapters.ElectronicsAdapter;
import com.example.e_commerceapp.models.Electronics;
import androidx.appcompat.app.AlertDialog;
import java.util.Collections;
import java.util.Comparator;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;


public class ElectronicsActivity extends AppCompatActivity {

    ListView listElectronics;

    ImageView imageElectronicsBack;
    ImageView ivCategories;
    ImageView imageElectronicsFilter;
    ImageView imageElectronicsSearch;

    ArrayList<Electronics> electronicsArrayList;
    ArrayList<Electronics> originalElectronicsList;
    ElectronicsAdapter electronicsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_electronics);

        // ربط عناصر الواجهة
        listElectronics = findViewById(R.id.listElectronics);
        imageElectronicsBack = findViewById(R.id.imageElectronicsBack);
        imageElectronicsFilter =
                findViewById(R.id.imageElectronicsFilter);
        imageElectronicsSearch =
                findViewById(R.id.imageElectronicsSearch);


        electronicsArrayList = new ArrayList<>();
        originalElectronicsList = new ArrayList<>();

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
        originalElectronicsList.addAll(electronicsArrayList);

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
        imageElectronicsFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(ElectronicsActivity.this)
                        .setTitle("Filter")
                        .setItems(new String[]{
                                "All",
                                "Price: Low to High",
                                "Price: High to Low"
                        }, (dialog, which) -> {

                            if (which == 0) {


                                electronicsArrayList.clear();

                                electronicsArrayList.addAll(originalElectronicsList);

                                electronicsAdapter.notifyDataSetChanged();

                            } else if (which == 1) {

                                Collections.sort(electronicsArrayList,
                                        Comparator.comparing(Electronics::getPrice));

                                electronicsAdapter.notifyDataSetChanged();

                            } else {


                                Collections.sort(electronicsArrayList,
                                        (e1, e2) -> Float.compare(e2.getPrice(), e1.getPrice()));

                                electronicsAdapter.notifyDataSetChanged();
                            }

                        })
                        .show();

            }
        });
        imageElectronicsSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editText = new EditText(ElectronicsActivity.this);

                new AlertDialog.Builder(ElectronicsActivity.this)
                        .setTitle("Search Product")
                        .setView(editText)

                        .setPositiveButton("Search", (dialog, which) -> {

                            String searchText = editText.getText().toString().trim();

                            ArrayList<Electronics> searchList = new ArrayList<>();

                            for (Electronics item : originalElectronicsList) {

                                if (item.getName().toLowerCase()
                                        .contains(searchText.toLowerCase())) {

                                    searchList.add(item);
                                }
                            }

                            if (searchList.isEmpty()) {

                                Toast.makeText(
                                        ElectronicsActivity.this,
                                        "No products found",
                                        Toast.LENGTH_SHORT
                                ).show();

                            } else {

                                electronicsArrayList.clear();
                                electronicsArrayList.addAll(searchList);
                                electronicsAdapter.notifyDataSetChanged();
                            }

                        })

                        .setNegativeButton("Cancel", null)
                        .show();

            }
        });
    }
}