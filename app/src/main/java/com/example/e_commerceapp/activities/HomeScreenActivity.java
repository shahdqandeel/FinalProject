package com.example.e_commerceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.R;

public class HomeScreenActivity extends AppCompatActivity {

    // التصنيفات
    LinearLayout layoutCategoryElectronics;
    LinearLayout layoutCategoryFashion;
    LinearLayout layoutCategoryShoes;
    LinearLayout layoutCategoryBeauty;
    LinearLayout layoutCategoryAccessories;
    LinearLayout layoutCategoryHome;

    // المنتجات المميزة
    LinearLayout layoutProductIphone;
    LinearLayout layoutProductHeadphone;
    LinearLayout layoutProductSunglasses;
    LinearLayout layoutProductDress;
    LinearLayout layoutProductShoes;
    LinearLayout layoutProductBeauty;

    // See All
    TextView tvCategoriesSeeAll;
    TextView tvFeaturedProductsSeeAll;

    // زر Shop Now
    Button btnShopNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_screen);

        // ربط التصنيفات
        layoutCategoryElectronics =
                findViewById(R.id.layout_category_electronics);

        layoutCategoryFashion =
                findViewById(R.id.layout_category_fashion);

        layoutCategoryShoes =
                findViewById(R.id.layout_category_shoes);

        layoutCategoryBeauty =
                findViewById(R.id.layout_category_beauty);

        layoutCategoryAccessories =
                findViewById(R.id.layout_category_accessories);

        layoutCategoryHome =
                findViewById(R.id.layout_category_home);

        // ربط المنتجات المميزة
        layoutProductIphone =
                findViewById(R.id.layout_product_iphone);

        layoutProductHeadphone =
                findViewById(R.id.layout_product_headphone);

        layoutProductSunglasses =
                findViewById(R.id.layout_product_sunglasses);

        layoutProductDress =
                findViewById(R.id.layout_product_dress);

        layoutProductShoes =
                findViewById(R.id.layout_product_shoes);

        layoutProductBeauty =
                findViewById(R.id.layout_product_beauty);

        tvCategoriesSeeAll =
                findViewById(R.id.tv_categories_see_all);

        tvFeaturedProductsSeeAll =
                findViewById(R.id.tv_featured_products_see_all);

        btnShopNow =
                findViewById(R.id.btn_shop_now);


        layoutCategoryElectronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        HomeScreenActivity.this,
                        ElectronicsActivity.class
                );

                startActivity(intent);
            }
        });

        // الضغط على Fashion
        layoutCategoryFashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        HomeScreenActivity.this,
                        FashionActivity.class
                );

                startActivity(intent);
            }
        });


        layoutCategoryShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        HomeScreenActivity.this,
                        ShoesActivity.class
                );

                startActivity(intent);
            }
        });


        layoutCategoryBeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        HomeScreenActivity.this,
                        BeautyActivity.class
                );

                startActivity(intent);
            }
        });

        // الضغط على Accessories
        layoutCategoryAccessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        HomeScreenActivity.this,
                        AccessoriesActivity.class
                );

                startActivity(intent);
            }
        });


        layoutCategoryHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        HomeScreenActivity.this,
                        HomeProductsActivity.class
                );

                startActivity(intent);
            }
        });

        tvCategoriesSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openCategoriesPage();
            }
        });

        // See All بجانب Featured Products
        tvFeaturedProductsSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openCategoriesPage();
            }
        });

        // زر Shop Now
        btnShopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openCategoriesPage();
            }
        });


        layoutProductIphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        HomeScreenActivity.this,
                        ElectronicsActivity.class
                );

                startActivity(intent);
            }
        });


        layoutProductHeadphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        HomeScreenActivity.this,
                        ElectronicsActivity.class
                );

                startActivity(intent);
            }
        });

        // الضغط على Sunglasses
        layoutProductSunglasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        HomeScreenActivity.this,
                        AccessoriesActivity.class
                );

                startActivity(intent);
            }
        });


        layoutProductDress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        HomeScreenActivity.this,
                        FashionActivity.class
                );

                startActivity(intent);
            }
        });


        layoutProductShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        HomeScreenActivity.this,
                        ShoesActivity.class
                );

                startActivity(intent);
            }
        });

        layoutProductBeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        HomeScreenActivity.this,
                        BeautyActivity.class
                );

                startActivity(intent);
            }
        });
    }

    private void openCategoriesPage() {

        Intent intent = new Intent(
                HomeScreenActivity.this,
                CategoriesActivity.class
        );

        startActivity(intent);
    }
}