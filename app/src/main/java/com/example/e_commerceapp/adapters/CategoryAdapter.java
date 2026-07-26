package com.example.e_commerceapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_commerceapp.R;
import com.example.e_commerceapp.models.Category;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    Context context;
    ArrayList<Category> categoryArrayList;
    LayoutInflater inflater;

    public CategoryAdapter(
            Context context,
            ArrayList<Category> categoryArrayList
    ) {

        this.context = context;
        this.categoryArrayList = categoryArrayList;

        inflater = (LayoutInflater)
                context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE
                );
    }

    @Override
    public int getCount() {
        return categoryArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categoryArrayList.get(position).getId();
    }

    @Override
    public View getView(
            int position,
            View convertView,
            ViewGroup parent
    ) {

        View root = inflater.inflate(
                R.layout.category_item_design,
                null
        );

        ImageView imageCategory =
                root.findViewById(R.id.image_category_item);

        TextView textCategoryName =
                root.findViewById(R.id.text_category_name);

        Category category =
                categoryArrayList.get(position);

        imageCategory.setImageResource(
                category.getImage()
        );

        textCategoryName.setText(
                category.getName()
        );

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        context,
                        category.getActivityClass()
                );

                context.startActivity(intent);
            }
        });

        return root;
    }
}