package com.example.e_commerceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.Collections;
import java.util.Comparator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.e_commerceapp.R;
import com.example.e_commerceapp.adapters.AccessoriesAdapter;
import com.example.e_commerceapp.models.Accessories;

import java.util.ArrayList;

public class AccessoriesActivity extends AppCompatActivity {

    ListView listAccessories;

    ImageView imageAccessoriesBack;
    ImageView imageAccessoriesSearch;
    ImageView imageAccessoriesFilter;
    ImageView ivCategories;

    TextView tvCategories;

    ArrayList<Accessories> accessoriesArrayList;
    ArrayList<Accessories> originalAccessoriesList;
    AccessoriesAdapter accessoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_accessories);

        // ربط عناصر الصفحة
        listAccessories = findViewById(R.id.listAccessories);
        imageAccessoriesBack = findViewById(R.id.imageAccessoriesBack);
        imageAccessoriesSearch = findViewById(R.id.imageAccessoriesSearch);
        imageAccessoriesFilter = findViewById(R.id.imageAccessoriesFilter);
        accessoriesArrayList = new ArrayList<>();
        originalAccessoriesList = new ArrayList<>();


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
        originalAccessoriesList.addAll(accessoriesArrayList);

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
        imageAccessoriesFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(AccessoriesActivity.this)
                        .setTitle("Filter")
                        .setItems(new String[]{
                                "All",
                                "Price: Low to High",
                                "Price: High to Low"
                        }, (dialog, which) -> {

                            if (which == 0) {

                                accessoriesArrayList.clear();
                                accessoriesArrayList.addAll(originalAccessoriesList);
                                accessoriesAdapter.notifyDataSetChanged();

                            } else if (which == 1) {

                                Collections.sort(
                                        accessoriesArrayList,
                                        Comparator.comparing(Accessories::getPrice)
                                );

                                accessoriesAdapter.notifyDataSetChanged();

                            } else {

                                Collections.sort(
                                        accessoriesArrayList,
                                        (a1, a2) -> Float.compare(a2.getPrice(), a1.getPrice())
                                );

                                accessoriesAdapter.notifyDataSetChanged();

                            }

                        })
                        .show();

            }
        });
        imageAccessoriesSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editText = new EditText(AccessoriesActivity.this);

                new AlertDialog.Builder(AccessoriesActivity.this)
                        .setTitle("Search Product")
                        .setView(editText)

                        .setPositiveButton("Search", (dialog, which) -> {

                            String searchText = editText.getText().toString().trim();

                            ArrayList<Accessories> searchList = new ArrayList<>();

                            for (Accessories item : originalAccessoriesList) {

                                if (item.getName().toLowerCase()
                                        .contains(searchText.toLowerCase())) {

                                    searchList.add(item);
                                }
                            }

                            if (searchList.isEmpty()) {

                                Toast.makeText(
                                        AccessoriesActivity.this,
                                        "No products found",
                                        Toast.LENGTH_SHORT
                                ).show();

                            } else {

                                accessoriesArrayList.clear();
                                accessoriesArrayList.addAll(searchList);
                                accessoriesAdapter.notifyDataSetChanged();
                            }

                        })

                        .setNegativeButton("Cancel", null)
                        .show();

            }
        });


    }
}