package com.example.e_commerceapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_commerceapp.activities.ProductDetailsActivity;
import com.example.e_commerceapp.R;
import com.example.e_commerceapp.models.Shoes;

import java.util.ArrayList;

public class ShoesAdapter extends BaseAdapter {

    Context context;
    ArrayList<Shoes> shoesArrayList;
    LayoutInflater inflater;

    public ShoesAdapter(
            Context context,
            ArrayList<Shoes> shoesArrayList
    ) {

        this.context = context;
        this.shoesArrayList = shoesArrayList;

        inflater = (LayoutInflater)
                context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE
                );
    }

    @Override
    public int getCount() {

        return shoesArrayList.size();
    }

    @Override
    public Object getItem(int position) {

        return shoesArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return shoesArrayList.get(position).getId();
    }

    @Override
    public View getView(
            int position,
            View convertView,
            ViewGroup parent
    ) {

        View root = inflater.inflate(
                R.layout.shoes_item_design,
                null
        );

        ImageView imageShoesItem =
                root.findViewById(R.id.image_shoes_item);

        TextView tvShoesName =
                root.findViewById(R.id.tv_shoes_name);

        TextView tvShoesPrice =
                root.findViewById(R.id.tv_shoes_price);

        TextView tvShoesRating =
                root.findViewById(R.id.tv_shoes_rating);

        Shoes shoes =
                shoesArrayList.get(position);

        imageShoesItem.setImageResource(
                shoes.getImage()
        );

        tvShoesName.setText(
                shoes.getName()
        );

        tvShoesPrice.setText(
                "$" + shoes.getPrice()
        );

        tvShoesRating.setText(
                String.valueOf(shoes.getRating())
        );

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);

                // إرسال تفاصيل المنتج
                intent.putExtra("product_name", shoesArrayList.get(position).getName());
                intent.putExtra("product_price", shoesArrayList.get(position).getPrice());
                intent.putExtra("product_image", shoesArrayList.get(position).getImage());
                intent.putExtra("product_rating", shoesArrayList.get(position).getRating());

                // إرسال اسم التصنيف
                intent.putExtra("category_name", "Shoes");

                context.startActivity(intent);
            }
        });

        return root;
    }
}