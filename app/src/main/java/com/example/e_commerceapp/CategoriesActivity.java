package com.example.e_commerceapp;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.adapters.CategoryAdapter;
import com.example.e_commerceapp.models.Category;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {

    // الشريط العلوي
    ImageView imageBack;
    ImageView imageTopCart;

    GridView gridCategories;

    ArrayList<Category> categoryArrayList;
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_categories);


        imageBack = findViewById(R.id.imageBack);
        imageTopCart = findViewById(R.id.imageTopCart);


        gridCategories = findViewById(R.id.gridCategories);

        categoryArrayList = new ArrayList<>();

        // Fashion
        categoryArrayList.add(
                new Category(
                        1,
                        "Fashion",
                        R.drawable.category_fashion,
                        FashionActivity.class
                )
        );

        // Electronics
        categoryArrayList.add(
                new Category(
                        2,
                        "Electronics",
                        R.drawable.category_electronics,
                        ElectronicsActivity.class
                )
        );

        // Shoes
        categoryArrayList.add(
                new Category(
                        3,
                        "Shoes",
                        R.drawable.category_shoes,
                        ShoesActivity.class
                )
        );

        // Beauty
        categoryArrayList.add(
                new Category(
                        4,
                        "Beauty",
                        R.drawable.category_beauty,
                        BeautyActivity.class
                )
        );

        // Accessories
        categoryArrayList.add(
                new Category(
                        5,
                        "Accessories",
                        R.drawable.category_accessories,
                        AccessoriesActivity.class
                )
        );

        // Home
        categoryArrayList.add(
                new Category(
                        6,
                        "Home",
                        R.drawable.category_home,
                        HomeProductsActivity.class
                )
        );


        categoryAdapter = new CategoryAdapter(
                CategoriesActivity.this,
                categoryArrayList
        );


        gridCategories.setAdapter(categoryAdapter);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
//        السلة
        imageTopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(
//                        requireContext(),
//                        CartActivity.class
//                );
//
//                startActivity(intent);
            }
        });
    }
}