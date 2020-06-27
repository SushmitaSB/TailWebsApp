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
    public void setSigninValidation(RealmManager realmManager, String name, String email, String pass, EditText nameEt, EditText emailEt, EditText passEt){

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
            try {

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (email.matches(emailPattern))
                {
                    realmManager.setRegistrationData(name,email,pass);
                   // status = true;

                }
                else
                {
                    Toast.makeText(context,"Invalid email address", Toast.LENGTH_SHORT).show();
                    RealmManager.STATUS = false;
                }

            }catch (Exception e){
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                RealmManager.STATUS = false;
            }
        }else if (TextUtils.isEmpty(name)){
            nameEt.setError("Please enter your name");
            RealmManager.STATUS = false;
        }else if (TextUtils.isEmpty(email)){
            RealmManager.STATUS = false;
            emailEt.setError("Please enter your email");
        }else if (TextUtils.isEmpty(pass)){
            passEt.setError("Please enter your password");
            RealmManager.STATUS = false;
        }

    }

}
