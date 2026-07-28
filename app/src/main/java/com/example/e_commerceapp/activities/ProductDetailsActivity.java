package com.example.e_commerceapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerceapp.R;
import com.example.e_commerceapp.models.CartItem;
import com.example.e_commerceapp.utils.CartManager;
import com.example.e_commerceapp.utils.FavoriteManager;
import com.example.e_commerceapp.models.Product;

public class ProductDetailsActivity extends AppCompatActivity {

    ImageView btn_back, btn_fav, btn_star, product_img;
    TextView product_name, product_price, product_rating, reviews, product_description, tv_quantity, minusItem, plusItem;
    LinearLayout section_color, colors_group, section_size, sizes_group, section_storage, storages_group;
    Button add_to_cart, buy_now;

    int quantity = 1;
    // متغيرات التحديد الحالية
    private View selectedColorView = null;
    private TextView selectedSizeView = null;
    private TextView selectedStorageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_details);
        initViews();
        receiveDataFromAdapters();
        setupClickListeners();
    }
    private void initViews() {
        btn_back = findViewById(R.id.iv_back);
        btn_fav = findViewById(R.id.iv_heart);
        btn_star = findViewById(R.id.iv_star);
        product_img = findViewById(R.id.iv_product_image);
        product_name = findViewById(R.id.tv_product_name);
        product_price = findViewById(R.id.tv_product_price);
        product_rating = findViewById(R.id.tv_product_rating);
        reviews = findViewById(R.id.tv_reviews);
        product_description = findViewById(R.id.tv_product_description);
        section_color = findViewById(R.id.section_color);
        colors_group = findViewById(R.id.colors_group);
        section_size = findViewById(R.id.section_size);
        sizes_group = findViewById(R.id.sizes_group);
        section_storage = findViewById(R.id.section_storage);
        storages_group = findViewById(R.id.storages_group);
        tv_quantity = findViewById(R.id.tv_quantity);
        minusItem = findViewById(R.id.tv_minus);
        plusItem = findViewById(R.id.tv_plus);
        add_to_cart = findViewById(R.id.btn_add_to_cart);
        buy_now = findViewById(R.id.btn_buy_now);
    }
    private void receiveDataFromAdapters() {
        Intent intent = getIntent();
        if (intent == null) return;

        String name = intent.getStringExtra("product_name");
        String price = extractPriceFromIntent(intent);
        int image = intent.getIntExtra("product_image", 0);
        float rating = intent.getFloatExtra("product_rating", 4.5f);
        String category = intent.getStringExtra("category_name");

        if (name != null) product_name.setText(name);
        product_price.setText(price);
        if (image != 0) product_img.setImageResource(image);
        product_rating.setText(String.valueOf(rating));

        setDetailsByName(name);

        if (category != null) {
            switch (category.toLowerCase()) {
                case "fashion":
                    showSection(true, true, false);
                    setupDefaultColors(new String[]{"#000000", "#7B3F00", "#D3D3D3"});
                    setupDefaultSizes(new String[]{"S", "M", "L", "XL"});
                    break;

                case "shoes":
                    showSection(true, true, false);
                    setupDefaultColors(new String[]{"#000000", "#FFFFFF", "#FF0000"});
                    setupDefaultSizes(new String[]{"7", "8", "9", "10", "11"});
                    break;

                case "electronics":
                    showSection(true, false, true);
                    setupDefaultColors(new String[]{"#1C1C1E", "#E3E4E5", "#007AFF"});
                    setupDefaultStorage(new String[]{"128GB", "256GB", "512GB"});
                    break;

                case "beauty":
                case "accessories":
                case "home":
                default:
                    showSection(true, false, false);
                    setupDefaultColors(new String[]{"#E6C6B8", "#D4AF37", "#8B4513"});
                    break;
            }
        }
    }
    private String extractPriceFromIntent(Intent intent) {
        if (intent.hasExtra("product_price_str")) {
            String strPrice = intent.getStringExtra("product_price_str");
            if (strPrice != null && !strPrice.isEmpty()) {
                return strPrice.startsWith("$") ? strPrice : "$" + strPrice;
            }
        }

        if (intent.hasExtra("product_price")) {
            double dPrice = intent.getDoubleExtra("product_price", 0.0);
            if (dPrice == 0.0) {
                dPrice = intent.getFloatExtra("product_price", 0.0f);
            }
            return String.format("$%.2f", dPrice);
        }

        return "$0.00";
    }

    private void showSection(boolean color, boolean size, boolean storage) {
        if (section_color != null) section_color.setVisibility(color ? View.VISIBLE : View.GONE);
        if (section_size != null) section_size.setVisibility(size ? View.VISIBLE : View.GONE);
        if (section_storage != null) section_storage.setVisibility(storage ? View.VISIBLE : View.GONE);
    }
    private void setupDefaultColors(String[] hexColors) {
        if (colors_group == null) return;
        colors_group.removeAllViews();
        selectedColorView = null;

        for (String hex : hexColors) {
            View circleView = new View(this);
            int size = (int) (32 * getResources().getDisplayMetrics().density);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
            params.setMargins(0, 0, (int) (12 * getResources().getDisplayMetrics().density), 0);
            circleView.setLayoutParams(params);

            // حفظ لون الهكس داخل التاج للاسترجاع
            circleView.setTag(hex);
            applyColorBackground(circleView, hex, false);

            circleView.setOnClickListener(v -> {
                if (selectedColorView != null) {
                    applyColorBackground(selectedColorView, (String) selectedColorView.getTag(), false);
                }
                applyColorBackground(v, hex, true);
                selectedColorView = v;
            });

            colors_group.addView(circleView);
        }
    }
    private void applyColorBackground(View view, String hexColor, boolean isSelected) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        try {
            shape.setColor(Color.parseColor(hexColor));
        } catch (Exception e) {
            shape.setColor(Color.BLACK);
        }

        if (isSelected) {
            // إطار بنفسجي سميك عند التحديد
            shape.setStroke((int) (3 * getResources().getDisplayMetrics().density), Color.parseColor("#6200EE"));
        } else {
            shape.setStroke((int) (1 * getResources().getDisplayMetrics().density), Color.parseColor("#DDDDDD"));
        }
        view.setBackground(shape);
    }

    private void setupDefaultSizes(String[] sizes) {
        if (sizes_group == null) return;
        sizes_group.removeAllViews();
        selectedSizeView = null;

        for (String size : sizes) {
            TextView tvSize = createOptionTextView(size, 50);

            tvSize.setOnClickListener(v -> {
                if (selectedSizeView != null) {
                    applyOptionStyle(selectedSizeView, false);
                }
                applyOptionStyle((TextView) v, true);
                selectedSizeView = (TextView) v;
            });

            sizes_group.addView(tvSize);
        }
    }

    private void setupDefaultStorage(String[] storages) {
        if (storages_group == null) return;
        storages_group.removeAllViews();
        selectedStorageView = null;

        for (String storage : storages) {
            TextView tvStorage = createOptionTextView(storage, 80);

            tvStorage.setOnClickListener(v -> {
                if (selectedStorageView != null) {
                    applyOptionStyle(selectedStorageView, false);
                }
                applyOptionStyle((TextView) v, true);
                selectedStorageView = (TextView) v;
            });

            storages_group.addView(tvStorage);
        }
    }
    private TextView createOptionTextView(String text, int widthDp) {
        TextView textView = new TextView(this);
        int width = (int) (widthDp * getResources().getDisplayMetrics().density);
        int height = (int) (42 * getResources().getDisplayMetrics().density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.setMargins(0, 0, (int) (10 * getResources().getDisplayMetrics().density), 0);
        textView.setLayoutParams(params);

        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(null, Typeface.BOLD);

        applyOptionStyle(textView, false);
        return textView;
    }

    private void applyOptionStyle(TextView textView, boolean isSelected) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(12 * getResources().getDisplayMetrics().density);

        if (isSelected) {
            shape.setColor(Color.parseColor("#1A6200EE")); // خلفية بنفسجية خفيفة
            shape.setStroke((int) (2 * getResources().getDisplayMetrics().density), Color.parseColor("#6200EE")); // إطار بنفسجي
            textView.setTextColor(Color.parseColor("#6200EE"));
        } else {
            shape.setColor(Color.parseColor("#F5F5F5"));
            shape.setStroke((int) (1 * getResources().getDisplayMetrics().density), Color.parseColor("#E0E0E0"));
            textView.setTextColor(Color.BLACK);
        }
        textView.setBackground(shape);
    }
    private void setDetailsByName(String name) {
        String desc = "High quality product crafted with premium materials. Perfect for everyday use.";
        int revCount = 86;

        if (name != null) {
            switch (name) {
                case "Leather Bag":
                    desc = "Chic and versatile shoulder bag made from premium vegan leather. Perfect for everyday use and special occasions.";
                    revCount = 86;
                    break;
                case "Glow Skin Care Set":
                    desc = "Complete 5-piece skincare set for a glowing, healthy and hydrated skin. Includes cleanser, toner, serum, moisturizer and eye cream.";
                    revCount = 76;
                    break;
                case "Modern Fabric Sofa":
                    desc = "Stylish and comfortable 3-seater sofa perfect for any living room. Made with high-quality fabric and sturdy wooden legs.";
                    revCount = 56;
                    break;
                case "Hoodie":
                    desc = "Comfortable and stylish hoodie made from premium cotton blend fabric. Perfect for everyday wear.";
                    revCount = 85;
                    break;
                case "Nike Air Max 270":
                    desc = "The Nike Air Max 270 delivers all-day comfort with a large Max Air unit for unrivaled cushioning.";
                    revCount = 98;
                    break;
                case "iPhone 15":
                    desc = "The iPhone 15 features a 6.1-inch Super Retina XDR display, A16 Bionic chip, and advanced dual-camera system.";
                    revCount = 120;
                    break;
            }
        }
        if (product_description != null) product_description.setText(desc);
        if (reviews != null) reviews.setText(" (" + revCount + " reviews)");
    }

    private void setupClickListeners() {
        // زر الرجوع
        btn_back.setOnClickListener(v -> finish());

        // الضغط على النجمة أو نص التقييم للتقييم التفاعلي
        if (btn_star != null) btn_star.setOnClickListener(v -> showRatingDialog());
        if (product_rating != null) product_rating.setOnClickListener(v -> showRatingDialog());

        // إضافة للمفضلة والانتقال لشاشة FavoritesActivity
        btn_fav.setOnClickListener(v -> {
            int productId = getIntent().getIntExtra("product_id", 0);
            String name = product_name.getText().toString();
            String price = product_price.getText().toString();
            int image = getIntent().getIntExtra("product_image", R.drawable.ic_launcher_background);
            float rating = extractRating();

            Product product = new Product(productId, name, price, image, rating);
            FavoriteManager.addToFavorites(product);

            Toast.makeText(ProductDetailsActivity.this, "Added to Favorites!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(ProductDetailsActivity.this, FavoriteActivity.class);
            startActivity(intent);
        });

        // زيادة/نقصان الكمية
        plusItem.setOnClickListener(v -> {
            quantity++;
            tv_quantity.setText(String.valueOf(quantity));
        });

        minusItem.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                tv_quantity.setText(String.valueOf(quantity));
            }
        });

        // إضافة للسلة
        add_to_cart.setOnClickListener(v -> {
            int productId = getIntent().getIntExtra("product_id",0);
            String name = product_name.getText().toString();
            String price = product_price.getText().toString();
            int image = getIntent().getIntExtra("product_image", R.drawable.ic_launcher_background);

            CartItem cartItem = new CartItem(productId, name, price, image, quantity);
            CartManager.addToCart(cartItem);

            Toast.makeText(ProductDetailsActivity.this, "Added (" + quantity + ") to Cart!", Toast.LENGTH_SHORT).show();
        });

        // الشراء المباشر والذهاب للـ Checkout
        buy_now.setOnClickListener(v -> {
            double cleanPrice = extractPriceAsDouble();
            double subtotalAmount = cleanPrice * quantity;
            double shippingAmount = 10.00;

            Intent intent = new Intent(ProductDetailsActivity.this, CheckoutActivity.class);
            intent.putExtra("subtotal_price", subtotalAmount);
            intent.putExtra("shipping_price", shippingAmount);
            startActivity(intent);
        });
    }

    // نافذة التقييم المنبثقة عند الضغط على النجمة
    private void showRatingDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Rate this product");

        final android.widget.RatingBar ratingBar = new android.widget.RatingBar(this);
        ratingBar.setNumStars(5);
        ratingBar.setStepSize(0.5f);
        ratingBar.setRating(extractRating());

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(60, 40, 60, 20);
        layout.setGravity(Gravity.CENTER);
        layout.addView(ratingBar);

        builder.setView(layout);

        builder.setPositiveButton("Submit", (dialog, which) -> {
            float newRating = ratingBar.getRating();

            // 1. تحديث التقييم في الشاشة الحالية
            product_rating.setText(String.valueOf(newRating));

            //2.تحديث التقييم في شاشة المفضلة باستخدام ID
            int productId = getIntent().getIntExtra("product_id",0);
            FavoriteManager.updateRating(productId, newRating);

            Toast.makeText(ProductDetailsActivity.this, "Rating updated!", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    // دالة مساعدة لاستخراج السعر كـ double بدون رموز
    private double extractPriceAsDouble() {
        try {
            String cleanPrice = product_price.getText().toString().replaceAll("[^0-9.]", "");
            return Double.parseDouble(cleanPrice);
        } catch (Exception e) {
            return 0.0;
        }
    }
    // دالة مساعدة لاستخراج التقييم كـ float
    private float extractRating() {
        try {
            return Float.parseFloat(product_rating.getText().toString());
        } catch (Exception e) {
            return 4.5f;
        }
    }
}