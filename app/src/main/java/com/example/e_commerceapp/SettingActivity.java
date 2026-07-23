package com.example.e_commerceapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingActivity extends AppCompatActivity {

    boolean isDarkModeOn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting);
        ImageView iv_dark_mode_switch = findViewById(R.id.iv_dark_mode_switch);
        //عند الضغط على switch
        iv_dark_mode_switch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                isDarkModeOn = !isDarkModeOn;
                if(isDarkModeOn){
                    iv_dark_mode_switch.setImageResource(R.drawable.ic_radio_on);
                }else{
                    iv_dark_mode_switch.setImageResource(R.drawable.ic_radio_off);
                }
            }
        });
    }
}