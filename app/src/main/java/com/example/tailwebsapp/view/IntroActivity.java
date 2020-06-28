package com.example.tailwebsapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tailwebsapp.R;
import com.example.tailwebsapp.controller.SharedPreferenceConfig;
import com.example.tailwebsapp.controller.ZoomOutPageTransformer;
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
    private Handler handler;
    private int delay = 6000; //milliseconds
    private int page = 0;
    Runnable runnable = new Runnable() {
        public void run() {
            if (slideViewPagerAdapter.getCount() == page) {
                page = 0;
            } else {
                page++;
            }
            viewPager.setCurrentItem(page, true);
            handler.postDelayed(this, delay);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        initializedVariables();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
        handler = new Handler();
        slideViewPagerAdapter = new SlideViewPagerAdapter(IntroActivity.this);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.setAdapter(slideViewPagerAdapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, delay);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}