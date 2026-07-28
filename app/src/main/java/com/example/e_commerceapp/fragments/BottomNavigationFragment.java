package com.example.e_commerceapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.e_commerceapp.activities.CartActivity;
import com.example.e_commerceapp.activities.CategoriesActivity;
import com.example.e_commerceapp.activities.FavoriteActivity;
import com.example.e_commerceapp.activities.HomeScreenActivity;
import com.example.e_commerceapp.R;
import com.example.e_commerceapp.activities.ProductDetailsActivity;
import com.example.e_commerceapp.activities.ProfileScreen;

public class BottomNavigationFragment extends Fragment {

    LinearLayout navHome;
    LinearLayout navCategories;
    LinearLayout navCart;
    LinearLayout navFavorites;
    LinearLayout navProfile;

    ImageView ivHome;
    ImageView ivCategories;
    ImageView ivCart;
    ImageView ivFavorites;
    ImageView ivProfile;

    TextView tvHome;
    TextView tvCategories;
    TextView tvCart;
    TextView tvFavorites;
    TextView tvProfile;

    public BottomNavigationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        View view = inflater.inflate(
                R.layout.fragment_bottom_navigation,
                container,
                false
        );

        // ربط أقسام الشريط
        navHome = view.findViewById(R.id.nav_home);
        navCategories = view.findViewById(R.id.nav_categories);
        navCart = view.findViewById(R.id.nav_cart);
        navFavorites = view.findViewById(R.id.nav_favorites);
        navProfile = view.findViewById(R.id.nav_profile);

        // ربط الأيقونات
        ivHome = view.findViewById(R.id.iv_home);
        ivCategories = view.findViewById(R.id.iv_categories);
        ivCart = view.findViewById(R.id.iv_cart);
        ivFavorites = view.findViewById(R.id.iv_favorites);
        ivProfile = view.findViewById(R.id.iv_profile);

        // ربط الكلمات
        tvHome = view.findViewById(R.id.tv_home);
        tvCategories = view.findViewById(R.id.tv_categories);
        tvCart = view.findViewById(R.id.tv_cart);
        tvFavorites = view.findViewById(R.id.tv_favorites);
        tvProfile = view.findViewById(R.id.tv_profile);

        makeAllGray();
        highlightCurrentPage();

        // الانتقال إلى Home
        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickedView) {

                if (!(requireActivity() instanceof HomeScreenActivity)) {

                    Intent intent = new Intent(
                            requireContext(),
                            HomeScreenActivity.class
                    );

                    startActivity(intent);
                }
            }
        });

        // الانتقال إلى Categories
        navCategories.setOnClickListener(new View.OnClickListener() {
             @Override
               public void onClick(View clickedView) {

                  if (!(requireActivity() instanceof CategoriesActivity)) {

                      Intent intent = new Intent(
                      requireContext(),
                      CategoriesActivity.class
                       );

                  startActivity(intent);
                  }
             }
        });

//         Cart
        navCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickedView) {

                if (!(requireActivity() instanceof CartActivity)) {

                    Intent intent = new Intent(
                            requireContext(),
                            CartActivity.class
                    );
                    startActivity(intent);
                }
            }
        });


        // Favorites
        navFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickedView) {
                if (!(requireActivity() instanceof FavoriteActivity)) {

                    Intent intent = new Intent(
                            requireContext(),
                            FavoriteActivity.class
                    );
                    startActivity(intent);
                }
            }
        });

        // Profile
        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickedView) {
                if (!(requireActivity() instanceof ProfileScreen)) {

                    Intent intent = new Intent(
                            requireContext(),
                            ProfileScreen.class
                    );
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    private void makeAllGray() {

        int grayColor = ContextCompat.getColor(
                requireContext(),
                R.color.gray_text
        );

        ivHome.setColorFilter(grayColor);
        ivCategories.setColorFilter(grayColor);
        ivCart.setColorFilter(grayColor);
        ivFavorites.setColorFilter(grayColor);
        ivProfile.setColorFilter(grayColor);

        tvHome.setTextColor(grayColor);
        tvCategories.setTextColor(grayColor);
        tvCart.setTextColor(grayColor);
        tvFavorites.setTextColor(grayColor);
        tvProfile.setTextColor(grayColor);
    }

    private void highlightCurrentPage() {

        int purpleColor = ContextCompat.getColor(
                requireContext(),
                R.color.purple_primary
        );

        if (requireActivity() instanceof HomeScreenActivity) {

            ivHome.setColorFilter(purpleColor);
            tvHome.setTextColor(purpleColor);

        } else if (requireActivity() instanceof CategoriesActivity){

            ivCategories.setColorFilter(purpleColor);
            tvCategories.setTextColor(purpleColor);
        }else if (requireActivity() instanceof CartActivity){

            ivCart.setColorFilter(purpleColor);
            tvCart.setTextColor(purpleColor);
        }else if (requireActivity() instanceof FavoriteActivity){

            ivFavorites.setColorFilter(purpleColor);
            tvFavorites.setTextColor(purpleColor);
        }else {

            ivProfile.setColorFilter(purpleColor);
            tvProfile.setTextColor(purpleColor);
        }
    }
}