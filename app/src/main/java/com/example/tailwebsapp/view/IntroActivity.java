package com.example.tailwebsapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tailwebsapp.R;
import com.example.tailwebsapp.controller.SharedPreferenceConfig;
import com.example.tailwebsapp.controller.adapter.SlideViewPagerAdapter;

public class IntroActivity extends AppCompatActivity {

    private TextView signIn;
    private LinearLayout loginLayout;
    private ViewPager viewPager;
    private SlideViewPagerAdapter slideViewPagerAdapter;
    private SharedPreferenceConfig sharedPreferenceConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        signIn = findViewById(R.id.signInId);
        loginLayout = findViewById(R.id.loginLayoutId);
        viewPager = findViewById(R.id.view_pager);
        sharedPreferenceConfig = new SharedPreferenceConfig(this);
        slideViewPagerAdapter = new SlideViewPagerAdapter(IntroActivity.this);
        viewPager.setAdapter(slideViewPagerAdapter);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }

        });

        loginLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}