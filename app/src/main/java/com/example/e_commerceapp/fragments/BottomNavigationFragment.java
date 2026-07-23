package com.example.e_commerceapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.e_commerceapp.CartActivity;
import com.example.e_commerceapp.FavoriteActivity;
import com.example.e_commerceapp.R;

public class BottomNavigationFragment extends Fragment {

    ImageView ivHome, ivCategories, ivCart, ivFavorites, ivProfile;
    TextView tvHome, tvCategories, tvCart, tvFavorites, tvProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bottom_navigation_fragment, container, false);

        // 1. تعريف الأيقونات والنصوص
        ivHome = view.findViewById(R.id.iv_home);
        tvHome = view.findViewById(R.id.tv_home);

        ivCategories = view.findViewById(R.id.iv_categories);
        tvCategories = view.findViewById(R.id.tv_categories);

        ivCart = view.findViewById(R.id.iv_cart);
        tvCart = view.findViewById(R.id.tv_cart);

        ivFavorites = view.findViewById(R.id.iv_favorites);
        tvFavorites = view.findViewById(R.id.tv_favorites);

        ivProfile = view.findViewById(R.id.iv_profile);
        tvProfile = view.findViewById(R.id.tv_profile);

        // 2. تحديث الألوان حسب الشاشة المفتوحة حالياً
        highlightCurrentTab();

        // 3. أداد الـ Click Listeners للتنقل (كما هي لديكِ)
        // ...

        return view;
    }

    private void highlightCurrentTab() {
        if (getActivity() == null) return;

        // إعادة جميع الأزرار إلى اللون الرمادي افتراضياً
        resetAllToGray();

        // فحص الشاشة الحالية وتلوينها بالبنفسجي
        if (getActivity() instanceof FavoriteActivity) {
            setSelected(ivFavorites, tvFavorites);
        } else if (getActivity() instanceof CartActivity) {
            setSelected(ivCart, tvCart);
        }
        // أضيفي باقي الـ Activities إذا وجد (HomeActivity, CategoriesActivity, ProfileActivity)
    }

    // دالة تجعل كل الأزرار رمادية
    private void resetAllToGray() {
        int grayColor = ContextCompat.getColor(requireContext(), R.color.gray_text);

        setItemColor(ivHome, tvHome, grayColor);
        setItemColor(ivCategories, tvCategories, grayColor);
        setItemColor(ivCart, tvCart, grayColor);
        setItemColor(ivFavorites, tvFavorites, grayColor);
        setItemColor(ivProfile, tvProfile, grayColor);
    }

    // دالة تميز العنصر النشط باللون البنفسجي
    private void setSelected(ImageView icon, TextView text) {
        int purpleColor = ContextCompat.getColor(requireContext(), R.color.purple_primary);
        setItemColor(icon, text, purpleColor);
    }

    // دالة تطبيق اللون على الأيقونة والنص معاً
    private void setItemColor(ImageView icon, TextView text, int color) {
        if (icon != null) {
            icon.setColorFilter(color); // تغيير لون صورة/أيقونة Vector
        }
        if (text != null) {
            text.setTextColor(color);  // تغيير لون النص
        }
    }
}