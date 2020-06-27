package com.example.tailwebsapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tailwebsapp.R;
import com.example.tailwebsapp.controller.RealmManager;
import com.example.tailwebsapp.controller.SharedPreferenceConfig;
import com.example.tailwebsapp.controller.adapter.StudentAdapter;
import com.example.tailwebsapp.model.studentDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
   @BindView(R.id.logoutid)
    TextView logout;
    @BindView(R.id.rvId)
    RecyclerView recyclerView;
    @BindView(R.id.fabId)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.toolbarId)
    Toolbar toolbar;
    @BindView(R.id.nodataId)
    ImageView imageView;
    public static final String TAG = "MainActivity";
    private RealmManager realmManager;
    private Realm realm;
    private SharedPreferenceConfig sharedPreferenceConfig;
    private StudentAdapter mAdapter;

    private RealmResults<studentDetails> realmResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing Variables
        initializedVariables();

        //log out button click
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferenceConfig.LoginStatus(false);
                Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // floating action button click
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudentForm.class);
                startActivity(intent);
            }
        });

        //fetching all  data from studebtDetails
        realmResults = realmManager.fetchStudentDetails();
        //set recyclerview
        if (realmResults.size() != 0){
            imageView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            mAdapter = new StudentAdapter(this, realmResults);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);
        }else {
            recyclerView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        }

        //whenever the database will update or change automatically addchangeListner will be triggered.
        realmResults.addChangeListener(new RealmChangeListener<RealmResults<studentDetails>>() {
            @Override
            public void onChange(RealmResults<studentDetails> studentDetails) {
                //set adapter
                recyclerView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
                mAdapter = new StudentAdapter(MainActivity.this, realmResults);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
            }
        });

    }

    private void initializedVariables() {
        ButterKnife.bind(this);

        // toolbar
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        sharedPreferenceConfig = new SharedPreferenceConfig(this);
        realm = Realm.getDefaultInstance();
        realmManager = new RealmManager(this, realm);
        //set layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
    }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}