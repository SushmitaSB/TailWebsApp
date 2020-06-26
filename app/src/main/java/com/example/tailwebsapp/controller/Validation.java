package com.example.tailwebsapp.controller;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tailwebsapp.view.RegistrationActivity;
import com.example.tailwebsapp.view.StudentForm;

import io.realm.Realm;

public class Validation {
    private Context context;
    private SharedPreferenceConfig sharedPreferenceConfig;
    private Realm realm;
    public Validation(Context context, Realm realm) {
        this.context = context;
        this.realm = realm;
    }

    //login field validation
    public void setLogInValidation(RealmManager realmManager, String email, String password, EditText emailEt, EditText passEt){
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            realmManager.fetchRegistrationData(email,password);
        }else if (TextUtils.isEmpty(email)){
            emailEt.setError("Please enter your email");
        }else if (TextUtils.isEmpty(password)){
            passEt.setError("Please enter your password");
        }
    }

    //StudentForm validation
    public boolean setFormValidation(RealmManager realmManager, String name, String sub, String marks, EditText nameEt, EditText subEt, EditText marksEt){
       boolean status = false;
        if (!TextUtils.isEmpty(name) &&!TextUtils.isEmpty(sub) && !TextUtils.isEmpty(marks) ){
            try {
                realmManager.setDataInStudentDetails(name, sub, marks);
                status = true;
            }catch (Exception ex){
                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
                status = false;
            }
        }else if(TextUtils.isEmpty(name)){
            nameEt.setError("Enter student name");
            status = false;
        }else if (TextUtils.isEmpty(sub)){
            subEt.setError("Enter subject name");
            status = false;
        }else if (TextUtils.isEmpty(marks)){
            marksEt.setError("Enter marks");
            status = false;
        }

        return status;
    }

    //Registration field validation
    public boolean setSigninValidation(RealmManager realmManager, String name, String email, String pass, EditText nameEt, EditText emailEt, EditText passEt){
        boolean status = false;
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
            try {
                realmManager.setRegistrationData(name,email,pass);
                status = true;
            }catch (Exception e){
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                status = false;
            }
        }else if (TextUtils.isEmpty(name)){
            nameEt.setError("Please enter your name");
            status = false;
        }else if (TextUtils.isEmpty(email)){
            status = false;
            emailEt.setError("Please enter your email");
        }else if (TextUtils.isEmpty(pass)){
            passEt.setError("Please enter your password");
            status = false;
        }
        return status;
    }

}
