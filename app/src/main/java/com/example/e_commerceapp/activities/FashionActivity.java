package com.example.e_commerceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.R;
import com.example.e_commerceapp.adapters.FashionAdapter;
import com.example.e_commerceapp.models.Fashion;

import java.util.ArrayList;

public class FashionActivity extends AppCompatActivity {

    ListView listFashion;
    ImageView imageFashionBack;

    ArrayList<Fashion> fashionArrayList;
    FashionAdapter fashionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fashion);

        // ربط عناصر الواجهة
        listFashion = findViewById(R.id.listFashion);
        imageFashionBack = findViewById(R.id.imageFashionBack);


        fashionArrayList = new ArrayList<>();


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
    }
}