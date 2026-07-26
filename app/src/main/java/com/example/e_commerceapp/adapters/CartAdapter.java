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

import com.example.e_commerceapp.CartActivity;
import com.example.e_commerceapp.ProductDetailsActivity;
import com.example.e_commerceapp.R;
import com.example.e_commerceapp.models.CartItem;
import com.example.e_commerceapp.models.CartManager;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    public interface OnCartChangeListener {

        void onItemChanged();
    }
    Context context;
    ArrayList<CartItem> cartList;

    private OnCartChangeListener listener;
    LayoutInflater inflater;

    public CartAdapter(Context context, ArrayList<CartItem> cartList, OnCartChangeListener listener){
        this.context=context;
        this.cartList=cartList;
        this.listener = listener;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount(){
        return cartList.size();
    }
    public Object getItem(int position){
        return cartList.get(position);
    }
    public long getItemId(int position){
        return cartList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root=inflater.inflate(R.layout.item_cart,null);
        ImageView product_img=root.findViewById(R.id.iv_product_image);
        TextView product_name=root.findViewById(R.id.tv_product_name);
        TextView product_price=root.findViewById(R.id.tv_product_price);
        TextView quantity=root.findViewById(R.id.tv_quantity);
        TextView minusItem=root.findViewById(R.id.tv_minus);
        TextView plusItem=root.findViewById(R.id.tv_plus);
        ImageView deleteItem=root.findViewById(R.id.iv_delete_icon);

        product_img.setImageResource(cartList.get(position).getImage());
        product_name.setText(cartList.get(position).getName());
        product_price.setText(cartList.get(position).getPrice()+"");
        quantity.setText(cartList.get(position).getQuantity()+"");

        if(minusItem != null){
            minusItem.setOnClickListener(v ->{
                if(cartList.get(position).getQuantity() > 1) {
                    cartList.get(position).setQuantity(cartList.get(position).getQuantity() - 1);
                    notifyDataSetChanged();
                    if (listener != null) listener.onItemChanged();
                }
            });
        }

        if(plusItem != null){
            plusItem.setOnClickListener(v ->{
                cartList.get(position).setQuantity(cartList.get(position).getQuantity() + 1);
                notifyDataSetChanged();
                if (listener != null) listener.onItemChanged();
            });
        }

        if(deleteItem != null){
            deleteItem.setOnClickListener(v ->{
                cartList.remove(position);
                notifyDataSetChanged();
                if (listener != null) listener.onItemChanged();
            });
        }
        return root;
    }
}
