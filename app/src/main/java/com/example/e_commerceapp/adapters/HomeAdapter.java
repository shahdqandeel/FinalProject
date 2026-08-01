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
import com.example.e_commerceapp.models.Home;

import java.util.ArrayList;

public class HomeAdapter extends BaseAdapter {

    Context context;
    ArrayList<Home> homeArrayList;
    LayoutInflater inflater;

    public HomeAdapter(
            Context context,
            ArrayList<Home> homeArrayList
    ) {

        this.context = context;
        this.homeArrayList = homeArrayList;

        inflater = (LayoutInflater)
                context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE
                );
    }

    @Override
    public int getCount() {
        return homeArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return homeArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return homeArrayList.get(position).getId();
    }

    @Override
    public View getView(
            int position,
            View convertView,
            ViewGroup parent
    ) {

        View root = inflater.inflate(
                R.layout.home_item_design,
                null
        );

        ImageView imageHomeItem =
                root.findViewById(R.id.image_home_item);
        ImageView imageFavorite =
                root.findViewById(R.id.image_home_favorite);

        TextView tvHomeName =
                root.findViewById(R.id.tv_home_name);

        TextView tvHomePrice =
                root.findViewById(R.id.tv_home_price);

        TextView tvHomeRating =
                root.findViewById(R.id.tv_home_rating);

        Home home =
                homeArrayList.get(position);

        imageHomeItem.setImageResource(
                home.getImage()
        );

        tvHomeName.setText(
                home.getName()
        );

        tvHomePrice.setText(
                "$" + home.getPrice()
        );

        tvHomeRating.setText(
                String.valueOf(home.getRating())
        );

        Product product = new Product(
                home.getId(),
                home.getName(),
                String.valueOf(home.getPrice()),
                home.getImage(),
                home.getRating()
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
                intent.putExtra("product_name", homeArrayList.get(position).getName());
                intent.putExtra("product_price", homeArrayList.get(position).getPrice());
                intent.putExtra("product_image", homeArrayList.get(position).getImage());
                intent.putExtra("product_rating", homeArrayList.get(position).getRating());

                // إرسال اسم التصنيف
                intent.putExtra("category_name", "Home");

                context.startActivity(intent);
            }
        });

        return root;
    }
}