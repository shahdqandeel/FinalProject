package com.example.e_commerceapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerceapp.models.Product;
import com.example.e_commerceapp.utils.FavoriteManager;

import com.example.e_commerceapp.activities.ProductDetailsActivity;
import com.example.e_commerceapp.R;
import com.example.e_commerceapp.models.Beauty;

import java.util.ArrayList;

public class BeautyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Beauty> beautyArrayList;
    LayoutInflater inflater;

    public BeautyAdapter(
            Context context,
            ArrayList<Beauty> beautyArrayList
    ) {

        this.context = context;
        this.beautyArrayList = beautyArrayList;

        inflater = (LayoutInflater)
                context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE
                );
    }

    @Override
    public int getCount() {
        return beautyArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return beautyArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return beautyArrayList.get(position).getId();
    }

    @Override
    public View getView(
            int position,
            View convertView,
            ViewGroup parent
    ) {

        View root = inflater.inflate(
                R.layout.beauty_item_design,
                null
        );

        ImageView imageBeautyItem =
                root.findViewById(R.id.image_beauty_item);
        ImageView imageFavorite =
                root.findViewById(R.id.image_beauty_favorite);
        TextView tvBeautyName =
                root.findViewById(R.id.tv_beauty_name);

        TextView tvBeautyPrice =
                root.findViewById(R.id.tv_beauty_price);

        TextView tvBeautyRating =
                root.findViewById(R.id.tv_beauty_rating);

        Beauty beauty =
                beautyArrayList.get(position);

        imageBeautyItem.setImageResource(
                beauty.getImage()
        );

        tvBeautyName.setText(
                beauty.getName()
        );

        tvBeautyPrice.setText(
                "$" + beauty.getPrice()
        );

        tvBeautyRating.setText(
                String.valueOf(beauty.getRating())
        );
        Product product = new Product(
                beauty.getId(),
                beauty.getName(),
                String.valueOf(beauty.getPrice()),
                beauty.getImage(),
                beauty.getRating()
        );

        imageFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageFavorite.setImageResource(R.drawable.ic_favorite_red);

                FavoriteManager.addToFavorites(product);

                Toast.makeText(
                        context,
                        "Added to Favorites",
                        Toast.LENGTH_SHORT
                ).show();

            }
        });

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);

                // إرسال تفاصيل المنتج
                intent.putExtra("product_name", beautyArrayList.get(position).getName());
                intent.putExtra("product_price", beautyArrayList.get(position).getPrice());
                intent.putExtra("product_image", beautyArrayList.get(position).getImage());
                intent.putExtra("product_rating", beautyArrayList.get(position).getRating());

                // إرسال اسم التصنيف
                intent.putExtra("category_name", "Beauty");

                context.startActivity(intent);
            }
        });

        return root;
    }
}