package com.example.tailwebsapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tailwebsapp.R;
import com.example.tailwebsapp.controller.RealmManager;
import com.example.tailwebsapp.controller.Validation;

import io.realm.Realm;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nameEt, emailEt, passEt;
    private String name, email, pass;
    private Button button;
    private Realm realm;
    private Validation validation;
    private RealmManager realmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        realm = Realm.getDefaultInstance();
        nameEt = findViewById(R.id.firstNameET);
        emailEt = findViewById(R.id.emailid);
        passEt = findViewById(R.id.passId);
        button = findViewById(R.id.btId);

        realmManager = new RealmManager(RegistrationActivity.this,realm);
        validation = new Validation(RegistrationActivity.this, realm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEt.getText().toString();
                email = emailEt.getText().toString();
                pass = passEt.getText().toString();
                validation.setSigninValidation(realmManager, name,email,pass,nameEt,emailEt,passEt);

            }
        });
    }
}