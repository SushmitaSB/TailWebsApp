package com.example.tailwebsapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tailwebsapp.R;
import com.example.tailwebsapp.controller.RealmManager;
import com.example.tailwebsapp.controller.Validation;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.loginId)
    TextView textViewLogin;

    @BindView(R.id.firstNameET)
    EditText nameEt;

    @BindView(R.id.emailid)
    EditText emailEt;

    @BindView(R.id.passId)
    EditText passEt;

    @BindView(R.id.conPassId)
    EditText conPassEt;

    @BindView(R.id.btId)
    Button button;

    private String name, email, pass, cpass;
    private Realm realm;
    private Validation validation;
    private RealmManager realmManager;
    private boolean status = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //intialize all the variables
        initializedVariables();

        //If user has an account then by clicking this textViewLogin would be able to go in LoginActivity;
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Using this explicit intent we will go from RegistrationActivity to LoginActivity.
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        //SignIn button click
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fetching all data from edittext
                fetchingDataFromEditText();
                //this method call will be helpful for validation
                 validation.setSigninValidation(realmManager, name,email,pass, cpass,nameEt,emailEt,passEt,conPassEt);
                if (RealmManager.STATUS){
                    //if registration or signin is sucessfull then activity will be finished
                    finish();
                }

            }
        });
    }

    private void fetchingDataFromEditText() {
        name = nameEt.getText().toString();
        email = emailEt.getText().toString();
        pass = passEt.getText().toString();
        cpass = conPassEt.getText().toString();
    }

    private void initializedVariables() {
        //butterknife initialization
        ButterKnife.bind(this);
        //realm object initialization
        realm = Realm.getDefaultInstance();
        //realmManager object initialization
        realmManager = new RealmManager(RegistrationActivity.this,realm);
        //validation object initialization
        validation = new Validation(RegistrationActivity.this, realm);
    }
}