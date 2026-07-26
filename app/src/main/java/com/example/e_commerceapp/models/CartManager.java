package com.example.e_commerceapp.models;

import java.util.ArrayList;

public class CartManager {

    // القائمة التخزينية للسلة في الذاكرة
    private static final ArrayList<CartItem> cartList = new ArrayList<>();

    // إرجاع كافة عناصر السلة
    public static ArrayList<CartItem> getCartList() {
        return cartList;
    }

        // إضافة منتج جديد أو تحديث كميته إذا كان موجوداً بالفعل
        public static void addToCart(CartItem newItem) {
            boolean isExist = false;

            for (CartItem item : cartList) {
                if (item.getName() != null && item.getName().equalsIgnoreCase(newItem.getName())) {
                    item.setQuantity(item.getQuantity() + newItem.getQuantity());
                    isExist = true;
                    break;
                }
            }
            // إذا كان المنتج جديداً وغير موجود بالسلة، يضاف كعنصر مستقل للقائمة
            if (!isExist) {
                cartList.add(newItem);
            }
        }
}
