package com.example.e_commerceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.R;
import com.example.e_commerceapp.adapters.FashionAdapter;
import com.example.e_commerceapp.models.Fashion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;

import androidx.core.content.ContextCompat;

public class FashionActivity extends AppCompatActivity {

    ListView listFashion;
    ImageView imageFashionBack;
    ImageView imageFashionSearch;
    ImageView imageFashionFilter;

    ArrayList<Fashion> fashionArrayList;
    ArrayList<Fashion> originalFashionList;
    FashionAdapter fashionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fashion);

        // ربط عناصر الواجهة
        listFashion = findViewById(R.id.listFashion);
        imageFashionBack = findViewById(R.id.imageFashionBack);
        imageFashionSearch = findViewById(R.id.imageFashionSearch);
        imageFashionFilter = findViewById(R.id.imageFashionFilter);



        fashionArrayList = new ArrayList<>();
        originalFashionList = new ArrayList<>();


        fashionArrayList.add(
                new Fashion(
                        1,
                        "Hoodie",
                        5,
                        45f,
                        R.drawable.img_hoodie
                )
        );


        fashionArrayList.add(
                new Fashion(
                        2,
                        "Evening Dress",
                        5,
                        120f,
                        R.drawable.evening_dress
                )
        );


        fashionArrayList.add(
                new Fashion(
                        3,
                        "Kids Yellow Dress",
                        4,
                        35f,
                        R.drawable.kids_yellow_dress
                )
        );


        fashionArrayList.add(
                new Fashion(
                        4,
                        "Jeans",
                        4,
                        40f,
                        R.drawable.jeans
                )
        );


        fashionArrayList.add(
                new Fashion(
                        5,
                        "White Blazer",
                        5,
                        75f,
                        R.drawable.white_blazer
                )
        );


        fashionArrayList.add(
                new Fashion(
                        6,
                        "Skirt",
                        4,
                        30f,
                        R.drawable.skirt
                )
        );


        fashionArrayList.add(
                new Fashion(
                        7,
                        "Summer Dress",
                        5,
                        50f,
                        R.drawable.product_dress
                )
        );
        originalFashionList.addAll(fashionArrayList);


        fashionAdapter = new FashionAdapter(
                FashionActivity.this,
                fashionArrayList
        );


        listFashion.setAdapter(fashionAdapter);


        imageFashionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        imageFashionFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(FashionActivity.this)
                        .setTitle("Filter")
                        .setItems(new String[]{
                                "All",
                                "Price: Low to High",
                                "Price: High to Low"
                        }, (dialog, which) -> {

                            if (which == 0) {

                                fashionArrayList.clear();
                                fashionArrayList.addAll(originalFashionList);
                                fashionAdapter.notifyDataSetChanged();

                            } else if (which == 1) {

                                Collections.sort(
                                        fashionArrayList,
                                        Comparator.comparing(Fashion::getPrice)
                                );

                                fashionAdapter.notifyDataSetChanged();

                            } else {

                                Collections.sort(
                                        fashionArrayList,
                                        (f1, f2) -> Float.compare(f2.getPrice(), f1.getPrice())
                                );

                                fashionAdapter.notifyDataSetChanged();

                            }

                        })
                        .show();

            }
        });
        // search
        imageFashionSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editText = new EditText(FashionActivity.this);

                new AlertDialog.Builder(FashionActivity.this)
                        .setTitle("Search Product")
                        .setView(editText)

                        .setPositiveButton("Search", (dialog, which) -> {

                            String searchText = editText.getText().toString().trim();

                            ArrayList<Fashion> searchList = new ArrayList<>();

                            for (Fashion item : originalFashionList) {

                                if (item.getName().toLowerCase()
                                        .contains(searchText.toLowerCase())) {

                                    searchList.add(item);
                                }
                            }

                            if (searchList.isEmpty()) {

                                Toast.makeText(
                                        FashionActivity.this,
                                        "No products found",
                                        Toast.LENGTH_SHORT
                                ).show();

                            } else {

                                fashionArrayList.clear();
                                fashionArrayList.addAll(searchList);
                                fashionAdapter.notifyDataSetChanged();
                            }

                        })

                        .setNegativeButton("Cancel", null)
                        .show();

            }
        });
    }
}