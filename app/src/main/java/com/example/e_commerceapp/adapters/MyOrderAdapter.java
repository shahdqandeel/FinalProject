package com.example.e_commerceapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_commerceapp.R;
import com.example.e_commerceapp.models.MyOrder;

import java.util.ArrayList;

public class MyOrderAdapter extends BaseAdapter {

    Context context;
    ArrayList<MyOrder> orderArrayList = new ArrayList<>();
    LayoutInflater inflater;

    public MyOrderAdapter(Context context, ArrayList<MyOrder> orderArrayList) {
        this.context = context;
        this.orderArrayList = orderArrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return orderArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return orderArrayList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View root = inflater.inflate(R.layout.my_order_design, parent, false);

        TextView tvOrderId = root.findViewById(R.id.tvOrderId);
        TextView tvOrderDate = root.findViewById(R.id.tvOrderDate);
        ImageView imgProduct = root.findViewById(R.id.imgProduct);
        TextView tvProductName = root.findViewById(R.id.tvProductName);
        TextView tvProductVariant = root.findViewById(R.id.tvProductVariant);
        TextView tvPrice = root.findViewById(R.id.tvPrice);
        TextView tvStatus = root.findViewById(R.id.tvStatus);
        TextView tvTotal = root.findViewById(R.id.tvTotal);

        // ربط العناصر بالبيانات
        tvOrderId.setText("Order #" + orderArrayList.get(position).getOrderId());
        tvOrderDate.setText(orderArrayList.get(position).getOrderDate());
        imgProduct.setImageResource(orderArrayList.get(position).getImageResId());
        tvProductName.setText(orderArrayList.get(position).getProductName());
        tvProductVariant.setText(orderArrayList.get(position).getProductVariant());
        tvPrice.setText("$" + orderArrayList.get(position).getPrice());
        tvStatus.setText(orderArrayList.get(position).getStatus());
        tvTotal.setText("Total: $" + orderArrayList.get(position).getTotalPrice()
                + " (" + orderArrayList.get(position).getItemCount() + " item)");

        return root;
    }
}