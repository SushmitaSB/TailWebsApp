package com.example.tailwebsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent newIntent = new Intent(SplashActivity.this, IntroActivity.class);
                startActivity(newIntent);
                finish();
            }
        },3000);
    }
}