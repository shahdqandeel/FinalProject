package com.example.e_commerceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.e_commerceapp.R;
import com.example.e_commerceapp.adapters.ShoesAdapter;
import com.example.e_commerceapp.models.Shoes;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class ShoesActivity extends AppCompatActivity {

    ListView listShoes;

    ImageView imageShoesBack;
    ImageView ivCategories;
    ImageView imageShoesSearch;
    ImageView imageShoesFilter;

    TextView tvCategories;

    ArrayList<Shoes> shoesArrayList;
    ArrayList<Shoes> originalShoesList;

    ShoesAdapter shoesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shoes);

        // ربط عناصر الصفحة
        listShoes = findViewById(R.id.listShoes);
        imageShoesBack = findViewById(R.id.imageShoesBack);
        imageShoesSearch = findViewById(R.id.imageShoesSearch);
        imageShoesFilter = findViewById(R.id.imageShoesFilter);

        shoesArrayList = new ArrayList<>();
        originalShoesList = new ArrayList<>();


        shoesArrayList.add(
                new Shoes(
                        1,
                        "Nike Air Max 270",
                        5,
                        129f,
                        R.drawable.img_nike_air_max_270
                )
        );


        shoesArrayList.add(
                new Shoes(
                        2,
                        "Sport Boot",
                        5,
                        109f,
                        R.drawable.sport_boot
                )
        );

        shoesArrayList.add(
                new Shoes(
                        3,
                        "Converse Chuck Taylor",
                        4,
                        65f,
                        R.drawable.product_shoes
                )
        );


        shoesArrayList.add(
                new Shoes(
                        4,
                        "Black High Heels",
                        5,
                        85f,
                        R.drawable.high_heels
                )
        );


        shoesArrayList.add(
                new Shoes(
                        5,
                        "Red Heels",
                        4,
                        75f,
                        R.drawable.red_heels
                )
        );


        shoesArrayList.add(
                new Shoes(
                        6,
                        "Premium Slipper",
                        5,
                        55f,
                        R.drawable.premium_slipper
                )
        );


        shoesArrayList.add(
                new Shoes(
                        7,
                        "Ankle Boot",
                        4,
                        95f,
                        R.drawable.ankle_boot
                )
        );
        originalShoesList.addAll(shoesArrayList);


        shoesAdapter = new ShoesAdapter(
                ShoesActivity.this,
                shoesArrayList
        );

        listShoes.setAdapter(shoesAdapter);

        imageShoesBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        imageShoesFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(ShoesActivity.this)
                        .setTitle("Filter")
                        .setItems(new String[]{
                                "All",
                                "Price: Low to High",
                                "Price: High to Low"
                        }, (dialog, which) -> {

                            if (which == 0) {

                                shoesArrayList.clear();
                                shoesArrayList.addAll(originalShoesList);
                                shoesAdapter.notifyDataSetChanged();

                            } else if (which == 1) {

                                Collections.sort(
                                        shoesArrayList,
                                        Comparator.comparing(Shoes::getPrice)
                                );

                                shoesAdapter.notifyDataSetChanged();

                            } else {

                                Collections.sort(
                                        shoesArrayList,
                                        (s1, s2) -> Float.compare(s2.getPrice(), s1.getPrice())
                                );

                                shoesAdapter.notifyDataSetChanged();

                            }

                        })
                        .show();

            }
        });
        imageShoesSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editText = new EditText(ShoesActivity.this);

                new AlertDialog.Builder(ShoesActivity.this)
                        .setTitle("Search Product")
                        .setView(editText)

                        .setPositiveButton("Search", (dialog, which) -> {

                            String searchText = editText.getText().toString().trim();

                            ArrayList<Shoes> searchList = new ArrayList<>();

                            for (Shoes item : originalShoesList) {

                                if (item.getName().toLowerCase()
                                        .contains(searchText.toLowerCase())) {

                                    searchList.add(item);
                                }
                            }

                            if (searchList.isEmpty()) {

                                Toast.makeText(
                                        ShoesActivity.this,
                                        "No products found",
                                        Toast.LENGTH_SHORT
                                ).show();

                            } else {

                                shoesArrayList.clear();
                                shoesArrayList.addAll(searchList);
                                shoesAdapter.notifyDataSetChanged();
                            }

                        })

                        .setNegativeButton("Cancel", null)
                        .show();

            }
        });
    }
}