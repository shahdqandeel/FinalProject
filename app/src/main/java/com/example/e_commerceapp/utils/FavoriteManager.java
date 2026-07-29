package com.example.e_commerceapp.utils;

import com.example.e_commerceapp.models.Product;

import java.util.ArrayList;
import java.util.Objects;

public class FavoriteManager {

    // القائمة التخزينية للمفضلة في الذاكرة
    private static final ArrayList<Product> favoriteList = new ArrayList<>();

    // إرجاع جميع عناصر المفضلة
    public static ArrayList<Product> getFavoriteList() {
        return favoriteList;
    }

    // إضافة منتج للمفضلة أو تحديث تقييمه إذا كان مضافاً مسبقاً
    public static void addToFavorites(Product newItem) {
        for (Product item : favoriteList) {
            if (item.getName() != null && item.getName().equalsIgnoreCase(newItem.getName())) {
                item.setRating(newItem.getRating());
                return;
            }
        }
        favoriteList.add(newItem);
    }

    // دالة تحديث التقييم ديناميكياً فور تغييره في صفحة التفاصيل عن طريق الـ ID
    public static void updateRating(String productName, float newRating) {
        for (Product item : favoriteList) {
            if (Objects.equals(item.getName(), productName)) {
                item.setRating(newRating);
                break;
            }
        }
    }

    // حذف عنصر من المفضلة
    public static void removeFromFavorites(Product item) {
        favoriteList.remove(item);
    }
}
