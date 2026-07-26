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
import com.example.e_commerceapp.models.Accessories;

import java.util.ArrayList;

public class AccessoriesAdapter extends BaseAdapter {

    Context context;
    ArrayList<Accessories> accessoriesArrayList;
    LayoutInflater inflater;

    public AccessoriesAdapter(
            Context context,
            ArrayList<Accessories> accessoriesArrayList
    ) {

        this.context = context;
        this.accessoriesArrayList = accessoriesArrayList;

        inflater = (LayoutInflater)
                context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE
                );
    }

    @Override
    public int getCount() {
        return accessoriesArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return accessoriesArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return accessoriesArrayList.get(position).getId();
    }

    @Override
    public View getView(
            int position,
            View convertView,
            ViewGroup parent
    ) {

        View root = inflater.inflate(
                R.layout.accessories_item_design,
                null
        );

        ImageView imageAccessoriesItem =
                root.findViewById(R.id.image_accessories_item);

        TextView tvAccessoriesName =
                root.findViewById(R.id.tv_accessories_name);

        TextView tvAccessoriesPrice =
                root.findViewById(R.id.tv_accessories_price);

        TextView tvAccessoriesRating =
                root.findViewById(R.id.tv_accessories_rating);

        Accessories accessories =
                accessoriesArrayList.get(position);

        imageAccessoriesItem.setImageResource(
                accessories.getImage()
        );

        tvAccessoriesName.setText(
                accessories.getName()
        );

        tvAccessoriesPrice.setText(
                "$" + accessories.getPrice()
        );

        tvAccessoriesRating.setText(
                String.valueOf(accessories.getRating())
        );

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);

                // إرسال تفاصيل المنتج
                intent.putExtra("product_name", accessoriesArrayList.get(position).getName());
                intent.putExtra("product_price", accessoriesArrayList.get(position).getPrice());
                intent.putExtra("product_image", accessoriesArrayList.get(position).getImage());
                intent.putExtra("product_rating", accessoriesArrayList.get(position).getRating());

                // إرسال اسم التصنيف
                intent.putExtra("category_name", "Accessories");

                context.startActivity(intent);
            }
        });
        
        

        return root;
    }
}