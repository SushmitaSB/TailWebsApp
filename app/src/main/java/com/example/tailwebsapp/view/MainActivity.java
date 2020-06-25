package com.example.tailwebsapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tailwebsapp.R;
import com.example.tailwebsapp.controller.RealmManager;
import com.example.tailwebsapp.controller.SharedPreferenceConfig;
import com.example.tailwebsapp.controller.adapter.StudentAdapter;
import com.example.tailwebsapp.model.studentDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    TextView logout;
    private RealmManager realmManager;
    private Realm realm;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private SharedPreferenceConfig sharedPreferenceConfig;
    private StudentAdapter mAdapter;
    private RealmResults<studentDetails> realmResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.fabId);
        recyclerView = findViewById(R.id.rvId);
        logout = findViewById(R.id.logoutid);
        sharedPreferenceConfig = new SharedPreferenceConfig(this);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferenceConfig.LoginStatus(false);
                Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(intent);
            }
        });
        realm = Realm.getDefaultInstance();
        realmManager = new RealmManager(this, realm);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudentForm.class);
                startActivity(intent);
            }
        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        realmResults = realmManager.fetchStudentDetails();
        if (null != realmResults){
            mAdapter = new StudentAdapter(this, realmResults);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);
        }

        realmResults.addChangeListener(new RealmChangeListener<RealmResults<studentDetails>>() {
            @Override
            public void onChange(RealmResults<studentDetails> studentDetails) {
                mAdapter = new StudentAdapter(MainActivity.this, realmResults);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
            }
        });

    }
}