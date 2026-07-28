package com.example.e_commerceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.R;
import com.example.e_commerceapp.adapters.BeautyAdapter;
import com.example.e_commerceapp.models.Beauty;

import java.util.ArrayList;

public class BeautyActivity extends AppCompatActivity {

    ListView listBeauty;

    ImageView imageBeautyBack;
    ImageView ivCategories;

    TextView tvCategories;

    ArrayList<Beauty> beautyArrayList;
    BeautyAdapter beautyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_beauty);

        // ربط عناصر الصفحة
        listBeauty = findViewById(R.id.listBeauty);
        imageBeautyBack = findViewById(R.id.imageBeautyBack);
        beautyArrayList = new ArrayList<>();

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
    }
}