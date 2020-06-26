package com.example.tailwebsapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tailwebsapp.R;
import com.example.tailwebsapp.controller.SharedPreferenceConfig;
import com.example.tailwebsapp.controller.adapter.SlideViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroActivity extends AppCompatActivity {

    @BindView(R.id.getStarted)
    Button bt;

    @BindView(R.id.signInId)
    TextView signIn;

    @BindView(R.id.loginLayoutId)
    LinearLayout loginLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private SlideViewPagerAdapter slideViewPagerAdapter;
    private String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        initializedVariables();

        //GetStarted button click
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        //Register Button click
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }

        });


        //login layout click
        loginLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void initializedVariables() {
        ButterKnife.bind(this);
        slideViewPagerAdapter = new SlideViewPagerAdapter(IntroActivity.this);
        viewPager.setAdapter(slideViewPagerAdapter);
    }
}