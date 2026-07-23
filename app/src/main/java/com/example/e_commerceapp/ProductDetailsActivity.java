package com.example.e_commerceapp;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.e_commerceapp.models.Product;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_details);
        ImageView product_img=findViewById(R.id.iv_product_image);
        TextView product_name=findViewById(R.id.tv_product_name);
        TextView product_price=findViewById(R.id.tv_product_price);
        TextView product_rating=findViewById(R.id.tv_product_rating);
        TextView reviews=findViewById(R.id.tv_reviews);
        TextView product_description=findViewById(R.id.tv_product_description);
        LinearLayout section_color=findViewById(R.id.section_color);
        LinearLayout colors_group=findViewById(R.id.colors_group);
        LinearLayout section_size=findViewById(R.id.section_size);
        LinearLayout sizes_group=findViewById(R.id.sizes_group);
        LinearLayout section_storage=findViewById(R.id.section_storage);
        LinearLayout storages_group=findViewById(R.id.storages_group);
        
        //استقبال كائن المنتج من الشاشة السابقة
        Product product = (Product)getIntent().getSerializableExtra("SELECTED_PRODUCT");
        if(product != null){
            product_img.setImageResource(product.getImage());
            product_name.setText(product.getName());
            product_price.setText(product.getPrice());
            product_rating.setText((int) product.getRating());
            reviews.setText(" ("+product.getReviewsCount()+"( reviews");
            product_description.setText(product.getDescription());
            if(product.getColors() != null){
                colors_group.removeAllViews();
                for(String color:product.getColors()){
                    View circleView = new View(this);
                    int size = (int)(28*getResources().getDisplayMetrics().density);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size,size);
                    params.setMargins(0,0,(int)(12*getResources().getDisplayMetrics().density),0);
                    circleView.setLayoutParams(params);

                    GradientDrawable shape = new GradientDrawable();
                    shape.setShape(GradientDrawable.OVAL);
                    shape.setColor(android.graphics.Color.parseColor(color));
                    //تستخدم لاضافة اطار خفيف جدا للالوان الفاتحة
                    shape.setStroke(1,android.graphics.Color.parseColor("#DDDDDD"));

                    circleView.setBackground(shape);
                    colors_group.addView(circleView);
                }
            }
            if(product.getSizes() != null && product.getSizes().length>0){
                section_size.setVisibility(View.VISIBLE);
                sizes_group.removeAllViews();
                for(String size : product.getSizes()){
                    TextView textView = new TextView(this);
                    int width = (int)(50*getResources().getDisplayMetrics().density);
                    int height = (int)(42*getResources().getDisplayMetrics().density);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
                    params.setMargins(0,0,(int)(8*getResources().getDisplayMetrics().density),0);
                    textView.setLayoutParams(params);

                    textView.setText(size);
                    textView.setGravity(android.view.Gravity.CENTER);
                    textView.setTextColor(android.graphics.Color.BLACK);
                    textView.setTypeface(null,android.graphics.Typeface.BOLD);
                    textView.setBackgroundResource(R.drawable.bg_quantity_counter);
                    sizes_group.addView(textView);
                }
            }else{
                sizes_group.setVisibility(View.GONE);
            }
            if(product.getStorages() != null && product.getStorages().length>0){
                section_storage.setVisibility(View.VISIBLE);
                storages_group.removeAllViews();
                for(String storage : product.getStorages()){
                    TextView textView = new TextView(this);
                    int width = (int)(80*getResources().getDisplayMetrics().density);
                    int height = (int)(42*getResources().getDisplayMetrics().density);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
                    params.setMargins(0,0,(int)(8*getResources().getDisplayMetrics().density),0);
                    textView.setLayoutParams(params);

                    textView.setText(storage);
                    textView.setGravity(android.view.Gravity.CENTER);
                    textView.setTextColor(android.graphics.Color.BLACK);
                    textView.setTypeface(null,android.graphics.Typeface.BOLD);
                    textView.setBackgroundResource(R.drawable.bg_quantity_counter);
                    storages_group.addView(textView);
                }
            }else{
                storages_group.setVisibility(View.GONE);
            }


        }

    }
}