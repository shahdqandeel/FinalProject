package com.example.e_commerceapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.e_commerceapp.models.Address;

import java.util.ArrayList;

public class AddressManager {

    // اسم "الدفتر" يلي رح نخزن فيه العناوين
    private static final String PREF_NAME = "NovaMartAddresses";
    private static final String KEY_ADDRESS_COUNT = "addressCount";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public AddressManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    // حفظ عنوان جديد
    public void addAddress(Address address) {
        // نجيب عدد العناوين المحفوظة حالياً
        int count = prefs.getInt(KEY_ADDRESS_COUNT, 0);

        // نخزن كل معلومة من العنوان الجديد برقم خاص فيها
        editor.putInt("address_" + count + "_id", address.getId());
        editor.putString("address_" + count + "_label", address.getLabel());
        editor.putString("address_" + count + "_fullName", address.getFullName());
        editor.putString("address_" + count + "_street", address.getStreetAddress());
        editor.putString("address_" + count + "_city", address.getCity());
        editor.putString("address_" + count + "_phone", address.getPhoneNumber());
        editor.putBoolean("address_" + count + "_isDefault", address.isDefault());

        // نزيد العداد بواحد حتى نعرف كم عنوان صار محفوظ
        editor.putInt(KEY_ADDRESS_COUNT, count + 1);
        editor.apply();
    }

    // إرجاع كل العناوين المحفوظة
    public ArrayList<Address> getAllAddresses() {
        ArrayList<Address> addressList = new ArrayList<>();
        int count = prefs.getInt(KEY_ADDRESS_COUNT, 0);

        // نمر على كل عنوان محفوظ ونرجعه من جديد كـ Address
        for (int i = 0; i < count; i++) {
            int id = prefs.getInt("address_" + i + "_id", 0);
            String label = prefs.getString("address_" + i + "_label", "");
            String fullName = prefs.getString("address_" + i + "_fullName", "");
            String street = prefs.getString("address_" + i + "_street", "");
            String city = prefs.getString("address_" + i + "_city", "");
            String phone = prefs.getString("address_" + i + "_phone", "");
            boolean isDefault = prefs.getBoolean("address_" + i + "_isDefault", false);

            addressList.add(new Address(id, label, fullName, street, city, phone, isDefault));
        }

        return addressList;
    }

    // جلب العنوان الافتراضي (أو أول عنوان إذا ما في افتراضي محدد)
    public Address getDefaultAddress() {
        ArrayList<Address> allAddresses = getAllAddresses();

        if (allAddresses.isEmpty()) {
            return null;
        }

        for (Address address : allAddresses) {
            if (address.isDefault()) {
                return address;
            }
        }

        // لو ما في عنوان معلّم كـ Default، رجعي أول عنوان بالقائمة
        return allAddresses.get(0);
    }

    // حذف عنوان معين بناءً على الـ id تبعه
    public void deleteAddress(int addressId) {
        ArrayList<Address> currentList = getAllAddresses();
        ArrayList<Address> updatedList = new ArrayList<>();

        // نحتفظ بكل العناوين ما عدا العنوان يلي بدنا نحذفه
        for (Address address : currentList) {
            if (address.getId() != addressId) {
                updatedList.add(address);
            }
        }

        // نمسح كل شي ونعيد كتابة القائمة الجديدة من الصفر
        rewriteAllAddresses(updatedList);
    }

    // تحديث عنوان موجود (بعد التعديل)
    public void updateAddress(Address updatedAddress) {
        ArrayList<Address> currentList = getAllAddresses();
        ArrayList<Address> updatedList = new ArrayList<>();

        for (Address address : currentList) {
            if (address.getId() == updatedAddress.getId()) {
                updatedList.add(updatedAddress); // نستبدل القديم بالجديد
            } else {
                updatedList.add(address);
            }
        }

        rewriteAllAddresses(updatedList);
    }

    // دالة مساعدة: تمسح كل شي وتعيد كتابة القائمة كاملة من جديد
    private void rewriteAllAddresses(ArrayList<Address> addressList) {
        editor.clear();
        editor.apply();

        for (Address address : addressList) {
            addAddress(address);
        }
    }
    // معرفة إذا كان فيه عناوين محفوظة أصلاً
    public boolean hasAddresses() {
        return prefs.getInt(KEY_ADDRESS_COUNT, 0) > 0;
    }
}
