package com.example.e_commerceapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerceapp.R;
import com.example.e_commerceapp.utils.FavoriteManager;
import com.example.e_commerceapp.models.Product;

import java.util.ArrayList;

public class FavoriteAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> productArrayList=new ArrayList<>();
    LayoutInflater inflater;
    public FavoriteAdapter(Context context,ArrayList<Product> productArrayList){
        this.context=context;
        this.productArrayList=productArrayList;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount(){
        return productArrayList.size();
    }
    public Object getItem(int position){
        return productArrayList.get(position);
    }
    public long getItemId(int position){
        return productArrayList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root=inflater.inflate(R.layout.item_favorite,null);
        ImageView product_img=root.findViewById(R.id.iv_product_image);
        TextView product_name=root.findViewById(R.id.tv_product_name);
        TextView product_price=root.findViewById(R.id.tv_product_price);
        TextView product_rating=root.findViewById(R.id.tv_product_rating);
        ImageView btn_fav = root.findViewById(R.id.iv_heart);

        product_img.setImageResource(productArrayList.get(position).getImage());
        product_name.setText(productArrayList.get(position).getName());
        product_price.setText(productArrayList.get(position).getPrice()+"");
        product_rating.setText(productArrayList.get(position).getRating()+"");

        if(btn_fav != null){
            btn_fav.setOnClickListener(v ->{
                FavoriteManager.removeFromFavorites(productArrayList.get(position));
                notifyDataSetChanged();
                Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show();
            });
        }

        return root;
    }

}
