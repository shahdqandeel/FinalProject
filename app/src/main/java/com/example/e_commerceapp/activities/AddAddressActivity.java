package com.example.e_commerceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.R;
import com.example.e_commerceapp.models.Address;
import com.example.e_commerceapp.utils.AddressManager;

public class AddAddressActivity extends AppCompatActivity {

    private EditText etFullName, etStreetAddress, etCity, etPhoneNumber;
    private RadioGroup radioGroupLabel;
    private CheckBox cbSetDefault;
    private AddressManager addressManager;

    private boolean isEditMode = false;
    private int editingAddressId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        addressManager = new AddressManager(this);

        ImageView btnBack = findViewById(R.id.btnBack);
        Button btnSaveAddress = findViewById(R.id.btnSaveAddress);

        etFullName = findViewById(R.id.etFullName);
        etStreetAddress = findViewById(R.id.etStreetAddress);
        etCity = findViewById(R.id.etCity);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        radioGroupLabel = findViewById(R.id.radioGroupLabel);
        cbSetDefault = findViewById(R.id.cbSetDefault);

        // نتحقق: هل جينا من زر Edit (فيه بيانات ممررة) أو Add New (صفحة فاضية)؟
        checkIfEditMode();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSaveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = etFullName.getText().toString().trim();
                String streetAddress = etStreetAddress.getText().toString().trim();
                String city = etCity.getText().toString().trim();
                String phoneNumber = etPhoneNumber.getText().toString().trim();

                if (fullName.isEmpty() || streetAddress.isEmpty() || city.isEmpty() || phoneNumber.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                int selectedLabelId = radioGroupLabel.getCheckedRadioButtonId();
                String label = "Home";
                if (selectedLabelId == R.id.radioWork) {
                    label = "Work";
                } else if (selectedLabelId == R.id.radioOther) {
                    label = "Other";
                }

                boolean isDefault = cbSetDefault.isChecked();

                if (isEditMode) {
                    // وضع التعديل: نحدث العنوان الموجود بنفس الـ id القديم
                    com.example.e_commerceapp.models.Address updatedAddress = new Address(editingAddressId, label, fullName, streetAddress, city, phoneNumber, isDefault);
                    addressManager.updateAddress(updatedAddress);
                    Toast.makeText(getApplicationContext(), "Address updated successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    // وضع الإضافة: عنوان جديد كلياً برقم جديد
                    int newId = (int) System.currentTimeMillis();
                    Address newAddress = new Address(newId, label, fullName, streetAddress, city, phoneNumber, isDefault);
                    addressManager.addAddress(newAddress);
                    Toast.makeText(getApplicationContext(), "Address saved successfully!", Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });
    }

    // إذا الصفحة انفتحت من زر Edit، نعبّي الحقول تلقائياً بالبيانات القديمة
    private void checkIfEditMode() {
        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEdit", false);

        if (isEditMode) {
            editingAddressId = intent.getIntExtra("addressId", -1);
            String label = intent.getStringExtra("label");
            String fullName = intent.getStringExtra("fullName");
            String street = intent.getStringExtra("street");
            String city = intent.getStringExtra("city");
            String phone = intent.getStringExtra("phone");
            boolean isDefault = intent.getBooleanExtra("isDefault", false);

            etFullName.setText(fullName);
            etStreetAddress.setText(street);
            etCity.setText(city);
            etPhoneNumber.setText(phone);
            cbSetDefault.setChecked(isDefault);

            if ("Work".equals(label)) {
                radioGroupLabel.check(R.id.radioWork);
            } else if ("Other".equals(label)) {
                radioGroupLabel.check(R.id.radioOther);
            } else {
                radioGroupLabel.check(R.id.radioHome);
            }
        }
    }
}