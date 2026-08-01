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
import com.example.e_commerceapp.adapters.BeautyAdapter;
import com.example.e_commerceapp.models.Beauty;

import java.util.ArrayList;

public class BeautyActivity extends AppCompatActivity {

    ListView listBeauty;

    ImageView imageBeautyBack;
    ImageView imageBeautySearch;
    ImageView imageBeautyFilter;
    ImageView ivCategories;

    TextView tvCategories;

    ArrayList<Beauty> beautyArrayList;
    ArrayList<Beauty> originalBeautyList;
    BeautyAdapter beautyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_beauty);

        // ربط عناصر الصفحة
        listBeauty = findViewById(R.id.listBeauty);
        imageBeautyBack = findViewById(R.id.imageBeautyBack);
        imageBeautySearch = findViewById(R.id.imageBeautySearch);
        imageBeautyFilter = findViewById(R.id.imageBeautyFilter);
        beautyArrayList = new ArrayList<>();
        originalBeautyList = new ArrayList<>();

        beautyArrayList.add(
                new Beauty(
                        1,
                        "Glow Skin Care Set",
                        5,
                        29f,
                        R.drawable.category_beauty
                )
        );


        beautyArrayList.add(
                new Beauty(
                        2,
                        "Chanel Coco",
                        5,
                        89f,
                        R.drawable.perfume_chanel
                )
        );


        beautyArrayList.add(
                new Beauty(
                        3,
                        "Matte Lipstick",
                        4,
                        12f,
                        R.drawable.matte_lipstick
                )
        );


        beautyArrayList.add(
                new Beauty(
                        4,
                        "Eyeshadow Palette",
                        5,
                        45f,
                        R.drawable.eyeshadow_palette
                )
        );


        beautyArrayList.add(
                new Beauty(
                        5,
                        "Foundation",
                        5,
                        18f,
                        R.drawable.product_beauty
                )
        );


        beautyArrayList.add(
                new Beauty(
                        6,
                        "Mascara",
                        4,
                        10f,
                        R.drawable.mascara
                )
        );


        beautyArrayList.add(
                new Beauty(
                        7,
                        "Cerave Cleanser",
                        5,
                        20f,
                        R.drawable.cerave_cleanser
                )
        );
        originalBeautyList.addAll(beautyArrayList);

        beautyAdapter = new BeautyAdapter(
                BeautyActivity.this,
                beautyArrayList
        );

        listBeauty.setAdapter(beautyAdapter);

        imageBeautyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        imageBeautyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(BeautyActivity.this)
                        .setTitle("Filter")
                        .setItems(new String[]{
                                "All",
                                "Price: Low to High",
                                "Price: High to Low"
                        }, (dialog, which) -> {

                            if (which == 0) {

                                beautyArrayList.clear();
                                beautyArrayList.addAll(originalBeautyList);
                                beautyAdapter.notifyDataSetChanged();

                            } else if (which == 1) {

                                Collections.sort(
                                        beautyArrayList,
                                        Comparator.comparing(Beauty::getPrice)
                                );

                                beautyAdapter.notifyDataSetChanged();

                            } else {

                                Collections.sort(
                                        beautyArrayList,
                                        (b1, b2) -> Float.compare(b2.getPrice(), b1.getPrice())
                                );

                                beautyAdapter.notifyDataSetChanged();

                            }

                        })
                        .show();

            }
        });
        imageBeautySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editText = new EditText(BeautyActivity.this);

                new AlertDialog.Builder(BeautyActivity.this)
                        .setTitle("Search Product")
                        .setView(editText)

                        .setPositiveButton("Search", (dialog, which) -> {

                            String searchText = editText.getText().toString().trim();

                            ArrayList<Beauty> searchList = new ArrayList<>();

                            for (Beauty item : originalBeautyList) {

                                if (item.getName().toLowerCase()
                                        .contains(searchText.toLowerCase())) {

                                    searchList.add(item);
                                }
                            }

                            if (searchList.isEmpty()) {

                                Toast.makeText(
                                        BeautyActivity.this,
                                        "No products found",
                                        Toast.LENGTH_SHORT
                                ).show();

                            } else {

                                beautyArrayList.clear();
                                beautyArrayList.addAll(searchList);
                                beautyAdapter.notifyDataSetChanged();
                            }

                        })

                        .setNegativeButton("Cancel", null)
                        .show();

            }
        });

    }
}