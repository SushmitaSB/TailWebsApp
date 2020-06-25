package com.example.tailwebsapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tailwebsapp.R;
import com.example.tailwebsapp.controller.Validation;
import com.example.tailwebsapp.model.Registration;
import com.example.tailwebsapp.controller.RealmManager;

import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEt, passEt;
    private Button button;
    TextView textView;
    private String email, password;
    private RealmManager realmManager;
    private Validation validation;
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEt = findViewById(R.id.input_email);
        passEt = findViewById(R.id.input_password);
        button = findViewById(R.id.btn_login);
        textView = findViewById(R.id.link_signup);
        realm = Realm.getDefaultInstance();
        realmManager = new RealmManager(this, realm);
        validation = new Validation(this, realm);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginActivity.this, Registration.class);
                startActivity(in);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailEt.getText().toString();
                password = passEt.getText().toString();
                validation.setLogInValidation(realmManager, email, password,emailEt,passEt);
            }
        });
    }
}