package com.example.e_commerceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.activities.AddAddressActivity;
import com.example.e_commerceapp.R;
import com.example.e_commerceapp.adapters.AddressAdapter;
import com.example.e_commerceapp.models.Address;
import com.example.e_commerceapp.utils.AddressManager;

import java.util.ArrayList;

public class MyAddressesActivity extends AppCompatActivity {

    private ListView listViewAddresses;
    private ArrayList<Address> addressList;
    private AddressAdapter adapter;
    private AddressManager addressManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addresses);

        addressManager = new AddressManager(this);

        ImageView btnBack = findViewById(R.id.btnBack);
        TextView btnAddNew = findViewById(R.id.btnAddNew);
        listViewAddresses = findViewById(R.id.listViewAddresses);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAddressesActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });

        loadAddresses();
    }

    // دالة بسيطة تجيب العناوين الحقيقية وتعرضها بالقائمة
    private void loadAddresses() {
        addressList = addressManager.getAllAddresses();

        if (addressList.isEmpty()) {
            Toast.makeText(this, "No addresses yet. Add your first address!", Toast.LENGTH_SHORT).show();
        }

        adapter = new AddressAdapter(this, addressList);
        listViewAddresses.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // كل مرة نرجع لهذه الصفحة (مثلاً بعد إضافة عنوان جديد)، نحدث القائمة
        loadAddresses();
    }
}
