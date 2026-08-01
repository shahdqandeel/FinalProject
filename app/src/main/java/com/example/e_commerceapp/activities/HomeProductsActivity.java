package com.example.e_commerceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.e_commerceapp.R;
import com.example.e_commerceapp.adapters.HomeAdapter;
import com.example.e_commerceapp.models.Home;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.Collections;
import java.util.Comparator;

import java.util.ArrayList;

public class HomeProductsActivity extends AppCompatActivity {

    ListView listHome;

    ImageView imageHomeBack;
    ImageView imageHomeSearch;
    ImageView imageHomeFilter;
    ImageView ivCategories;

    TextView tvCategories;

    ArrayList<Home> homeArrayList;
    ArrayList<Home> originalHomeList;
    HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_products);

        // ربط عناصر الصفحة
        listHome = findViewById(R.id.listHome);
        imageHomeBack = findViewById(R.id.imageHomeBack);
        imageHomeSearch = findViewById(R.id.imageHomeSearch);
        imageHomeFilter = findViewById(R.id.imageHomeFilter);


        homeArrayList = new ArrayList<>();
        originalHomeList = new ArrayList<>();

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
        originalHomeList.addAll(homeArrayList);

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
        imageHomeFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(HomeProductsActivity.this)
                        .setTitle("Filter")
                        .setItems(new String[]{
                                "All",
                                "Price: Low to High",
                                "Price: High to Low"
                        }, (dialog, which) -> {

                            if (which == 0) {

                                homeArrayList.clear();
                                homeArrayList.addAll(originalHomeList);
                                homeAdapter.notifyDataSetChanged();

                            } else if (which == 1) {

                                Collections.sort(
                                        homeArrayList,
                                        Comparator.comparing(Home::getPrice)
                                );

                                homeAdapter.notifyDataSetChanged();

                            } else {

                                Collections.sort(
                                        homeArrayList,
                                        (h1, h2) -> Float.compare(h2.getPrice(), h1.getPrice())
                                );

                                homeAdapter.notifyDataSetChanged();

                            }

                        })
                        .show();

            }
        });
        imageHomeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editText = new EditText(HomeProductsActivity.this);

                new AlertDialog.Builder(HomeProductsActivity.this)
                        .setTitle("Search Product")
                        .setView(editText)

                        .setPositiveButton("Search", (dialog, which) -> {

                            String searchText = editText.getText().toString().trim();

                            ArrayList<Home> searchList = new ArrayList<>();

                            for (Home item : originalHomeList) {

                                if (item.getName().toLowerCase()
                                        .contains(searchText.toLowerCase())) {

                                    searchList.add(item);
                                }
                            }

                            if (searchList.isEmpty()) {

                                Toast.makeText(
                                        HomeProductsActivity.this,
                                        "No products found",
                                        Toast.LENGTH_SHORT
                                ).show();

                            } else {

                                homeArrayList.clear();
                                homeArrayList.addAll(searchList);
                                homeAdapter.notifyDataSetChanged();
                            }

                        })

                        .setNegativeButton("Cancel", null)
                        .show();

            }
        });
    }
}