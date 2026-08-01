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
import com.example.e_commerceapp.models.Electronics;
import com.example.e_commerceapp.models.Product;
import com.example.e_commerceapp.utils.FavoriteManager;
import com.example.e_commerceapp.models.Product;
import com.example.e_commerceapp.utils.FavoriteManager;
import android.widget.Toast;

import java.util.ArrayList;

public class ElectronicsAdapter extends BaseAdapter {

    Context context;
    ArrayList<Electronics> electronicsArrayList;
    LayoutInflater inflater;

    public ElectronicsAdapter(
            Context context,
            ArrayList<Electronics> electronicsArrayList
    ) {

        this.context = context;
        this.electronicsArrayList = electronicsArrayList;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return electronicsArrayList.size();
    }

    @Override
    public Object getItem(int position) {

        return electronicsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return electronicsArrayList.get(position).getId();
    }

    @Override
    public View getView(
            int position,
            View convertView,
            ViewGroup parent
    ) {

        View root = inflater.inflate(
                R.layout.electronics_item_design,
                null
        );

        ImageView imageItem =
                root.findViewById(R.id.image_item);
        ImageView imageFavorite =
                root.findViewById(R.id.image_favorite);

        TextView tvName =
                root.findViewById(R.id.tv_name);

        TextView tvPrice =
                root.findViewById(R.id.tv_price);

        TextView tvRating =
                root.findViewById(R.id.tv_rating);

        Electronics electronics =
                electronicsArrayList.get(position);
        final boolean[] isFavorite = {false};

        imageItem.setImageResource(
                electronics.getImage()
        );

        tvName.setText(
                electronics.getName()
        );

        tvPrice.setText(
                "$" + electronics.getPrice()
        );

        tvRating.setText(
                String.valueOf(electronics.getRating())
        );
        Product product = new Product(
                electronics.getId(),
                electronics.getName(),
                String.valueOf(electronics.getPrice()),
                electronics.getImage(),
                electronics.getRating()
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
                intent.putExtra("product_name", electronicsArrayList.get(position).getName());
                intent.putExtra("product_price", electronicsArrayList.get(position).getPrice());
                intent.putExtra("product_image", electronicsArrayList.get(position).getImage());
                intent.putExtra("product_rating", electronicsArrayList.get(position).getRating());

                // إرسال اسم التصنيف
                intent.putExtra("category_name", "Electronics");

                context.startActivity(intent);
            }
        });

        return root;
    }
}