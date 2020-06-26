package com.example.tailwebsapp.controller;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.tailwebsapp.view.LoginActivity;
import com.example.tailwebsapp.view.MainActivity;
import com.example.tailwebsapp.model.Registration;
import com.example.tailwebsapp.model.studentDetails;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmManager {
    private RealmResults<studentDetails> realmResults;
    private Context context;
    private Realm realm;
    private SharedPreferenceConfig sharedPreferenceConfig;

    //constructor
    public RealmManager(Context context, Realm realm) {
        this.context = context;
        this.realm = realm;
    }
    // fetching student details from realm database
    public RealmResults<studentDetails> fetchStudentDetails(){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realmResults = realm.where(studentDetails.class).findAll();
            }
        });

        return realmResults;
    }

    //set student data in realm database
    public void setDataInStudentDetails(String name, String sub, String marks){
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    String id = name + sub;
                    studentDetails details = realm.where(studentDetails.class).equalTo(studentDetails.ID, id).findFirst();
                    if (null == details) {
                        studentDetails studentDetails = new studentDetails();
                        studentDetails.setId(id);
                        studentDetails.setName(name);
                        studentDetails.setSubject(sub);
                        studentDetails.setMarks(marks);
                        realm.insertOrUpdate(studentDetails);
                    }else {
                        details.setMarks(marks);
                        realm.insertOrUpdate(details);
                    }
                }
            });

    }

    //fetch registration data from database
    public void fetchRegistrationData(String email, String pass){
        sharedPreferenceConfig = new SharedPreferenceConfig(context);
        Registration registration = realm.where(Registration.class).equalTo(Registration.EMAIL, email).findFirst();
        if (null != registration){
            if (pass.equals(registration.getPass())){
                Toast.makeText(context, "Login Succesfull", Toast.LENGTH_SHORT).show();
                sharedPreferenceConfig.LoginStatus(true);
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }else {
                Toast.makeText(context, "Password does not exist", Toast.LENGTH_SHORT).show();
                sharedPreferenceConfig.LoginStatus(false);
            }
        }else {
            Toast.makeText(context, "Email does not exist", Toast.LENGTH_SHORT).show();
            sharedPreferenceConfig.LoginStatus(false);
        }

    }

    //set registraion data in database
    public void setRegistrationData(String name, String Email, String pass){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Registration reg = realm.where(Registration.class).equalTo(Registration.EMAIL, Email).findFirst();
                if (null == reg){
                    Registration registration = new Registration();
                    // increment index
                    Number currentIdNum = realm.where(Registration.class).max(registration.ID);
                    int nextId;
                    if(null == currentIdNum) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    registration.setId(nextId);
                    registration.setName(name);
                    registration.setEmail(Email);
                    registration.setPass(pass);
                    realm.insertOrUpdate(registration);
                    Toast.makeText(context, "Registration Successfull", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, LoginActivity.class);
                    context.startActivity(i);
                }else {
                    Toast.makeText(context, "This email is already used", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
