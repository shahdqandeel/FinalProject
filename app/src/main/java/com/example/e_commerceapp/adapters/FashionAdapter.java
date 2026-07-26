package com.example.e_commerceapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_commerceapp.ProductDetailsActivity;
import com.example.e_commerceapp.R;
import com.example.e_commerceapp.models.Fashion;

import java.util.ArrayList;

public class FashionAdapter extends BaseAdapter {

    Context context;
    ArrayList<Fashion> fashionArrayList;
    LayoutInflater inflater;

    public FashionAdapter(
            Context context,
            ArrayList<Fashion> fashionArrayList
    ) {

        this.context = context;
        this.fashionArrayList = fashionArrayList;

        inflater = (LayoutInflater)
                context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE
                );
    }

    @Override
    public int getCount() {
        return fashionArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return fashionArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return fashionArrayList.get(position).getId();
    }

    @Override
    public View getView(
            int position,
            View convertView,
            ViewGroup parent
    ) {

        View root = inflater.inflate(
                R.layout.fashion_item_design,
                null
        );

        ImageView imageFashionItem =
                root.findViewById(R.id.image_fashion_item);

        TextView tvFashionName =
                root.findViewById(R.id.tv_fashion_name);

        TextView tvFashionPrice =
                root.findViewById(R.id.tv_fashion_price);

        TextView tvFashionRating =
                root.findViewById(R.id.tv_fashion_rating);

        Fashion fashion =
                fashionArrayList.get(position);

        imageFashionItem.setImageResource(
                fashion.getImage()
        );

        tvFashionName.setText(
                fashion.getName()
        );

        tvFashionPrice.setText(
                "$" + fashion.getPrice()
        );

        tvFashionRating.setText(
                String.valueOf(fashion.getRating())
        );
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);

                // إرسال تفاصيل المنتج
                intent.putExtra("product_name", fashionArrayList.get(position).getName());
                intent.putExtra("product_price", fashionArrayList.get(position).getPrice());
                intent.putExtra("product_image", fashionArrayList.get(position).getImage());
                intent.putExtra("product_rating", fashionArrayList.get(position).getRating());

                // إرسال اسم التصنيف
                intent.putExtra("category_name", "Fashion");

                context.startActivity(intent);
            }
        });

        return root;
    }
}