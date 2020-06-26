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
import com.example.tailwebsapp.controller.SharedPreferenceConfig;
import com.example.tailwebsapp.controller.Validation;
import com.example.tailwebsapp.model.Registration;
import com.example.tailwebsapp.controller.RealmManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.input_email)
    EditText emailEt;
    @BindView(R.id.input_password)
    EditText passEt;
    @BindView(R.id.btn_login)
    Button button;
    @BindView(R.id.link_signup)
    TextView textView;
    private String email, password;
    private RealmManager realmManager;
    private Validation validation;
    private Realm realm;
    private SharedPreferenceConfig sharedPreferenceConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //initialized variables
        initializedVariables();

        //signin text click
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        //login button click
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailEt.getText().toString();
                password = passEt.getText().toString();
                validation.setLogInValidation(realmManager, email, password,emailEt,passEt);
                if (sharedPreferenceConfig.read_login_status()){
                    finish();
                }

            }
        });
    }

    private void initializedVariables() {
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        realmManager = new RealmManager(this, realm);
        validation = new Validation(this, realm);
        sharedPreferenceConfig = new SharedPreferenceConfig(this);
    }
}