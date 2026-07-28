package com.example.e_commerceapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.e_commerceapp.activities.AddAddressActivity;
import com.example.e_commerceapp.R;
import com.example.e_commerceapp.models.Address;
import com.example.e_commerceapp.utils.AddressManager;

import java.util.ArrayList;

public class AddressAdapter extends BaseAdapter {

    Context context;
    ArrayList<Address> addressArrayList = new ArrayList<>();
    LayoutInflater inflater;
    AddressManager addressManager;

    public AddressAdapter(Context context, ArrayList<Address> addressArrayList) {
        this.context = context;
        this.addressArrayList = addressArrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        addressManager = new AddressManager(context);
    }

    @Override
    public int getCount() {
        return addressArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return addressArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return addressArrayList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View root = inflater.inflate(R.layout.address_design, parent, false);

        TextView tvLabel = root.findViewById(R.id.tvLabel);
        TextView tvDefaultBadge = root.findViewById(R.id.tvDefaultBadge);
        TextView tvFullName = root.findViewById(R.id.tvFullName);
        TextView tvFullAddress = root.findViewById(R.id.tvFullAddress);
        TextView tvPhoneNumber = root.findViewById(R.id.tvPhoneNumber);
        LinearLayout btnEdit = root.findViewById(R.id.btnEdit);
        LinearLayout btnDelete = root.findViewById(R.id.btnDelete);

        final Address currentAddress = addressArrayList.get(position);

        tvLabel.setText(currentAddress.getLabel());
        tvFullName.setText(currentAddress.getFullName());
        tvFullAddress.setText(currentAddress.getStreetAddress() + ", " + currentAddress.getCity());
        tvPhoneNumber.setText(currentAddress.getPhoneNumber());

        if (currentAddress.isDefault()) {
            tvDefaultBadge.setVisibility(View.VISIBLE);
        } else {
            tvDefaultBadge.setVisibility(View.GONE);
        }

        // زر Edit: يفتح صفحة Add Address مع تمرير بيانات العنوان الحالي
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddAddressActivity.class);
                intent.putExtra("isEdit", true);
                intent.putExtra("addressId", currentAddress.getId());
                intent.putExtra("label", currentAddress.getLabel());
                intent.putExtra("fullName", currentAddress.getFullName());
                intent.putExtra("street", currentAddress.getStreetAddress());
                intent.putExtra("city", currentAddress.getCity());
                intent.putExtra("phone", currentAddress.getPhoneNumber());
                intent.putExtra("isDefault", currentAddress.isDefault());
                context.startActivity(intent);
            }
        });

        // زر Delete: يسأل تأكيد ثم يحذف
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Address")
                        .setMessage("Are you sure you want to delete this address?")
                        .setPositiveButton("Yes", new android.content.DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(android.content.DialogInterface dialog, int which) {
                                addressManager.deleteAddress(currentAddress.getId());

                                // نحذف العنوان من القائمة المعروضة حالياً ونحدث الشاشة فوراً
                                addressArrayList.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });

        return root;
    }
}
