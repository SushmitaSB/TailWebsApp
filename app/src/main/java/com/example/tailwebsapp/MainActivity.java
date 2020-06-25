package com.example.tailwebsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tailwebsapp.controller.RealmManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    private RealmManager realmManager;
    private Realm realm;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private StudentAdapter mAdapter;
    private RealmResults<studentDetails> realmResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.fabId);
        recyclerView = findViewById(R.id.rvId);
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