package com.example.e_commerceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.R;
import com.example.e_commerceapp.adapters.ShoesAdapter;
import com.example.e_commerceapp.models.Shoes;

import java.util.ArrayList;

public class ShoesActivity extends AppCompatActivity {

    ListView listShoes;

    ImageView imageShoesBack;
    ImageView ivCategories;

    TextView tvCategories;

    ArrayList<Shoes> shoesArrayList;
    ShoesAdapter shoesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shoes);

        // ربط عناصر الصفحة
        listShoes = findViewById(R.id.listShoes);
        imageShoesBack = findViewById(R.id.imageShoesBack);

        shoesArrayList = new ArrayList<>();


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
    }
}