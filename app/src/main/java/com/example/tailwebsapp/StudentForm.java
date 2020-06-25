package com.example.tailwebsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tailwebsapp.controller.RealmManager;

import io.realm.Realm;

public class StudentForm extends AppCompatActivity {

    EditText nameEt, subEt, marksEt;
    Button button;
    private String name, sub, marks;
    private RealmManager realmManager;
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);
        nameEt = findViewById(R.id.input_name);
        subEt = findViewById(R.id.input_subject);
        marksEt = findViewById(R.id.input_marks);
        button = findViewById(R.id.btn_submit);
        realm = Realm.getDefaultInstance();
        realmManager = new RealmManager(this, realm);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEt.getText().toString();
                sub = subEt.getText().toString();
                marks = marksEt.getText().toString();
                if (!TextUtils.isEmpty(name) &&!TextUtils.isEmpty(sub) && !TextUtils.isEmpty(marks) ){
                    try {
                        realmManager.setDataInStudentDetails(name, sub, marks);
                        finish();
                    }catch (Exception ex){
                        Toast.makeText(StudentForm.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else if(TextUtils.isEmpty(name)){
                    nameEt.setError("Enter student name");
                }else if (TextUtils.isEmpty(sub)){
                    subEt.setError("Enter subject name");
                }else if (TextUtils.isEmpty(marks)){
                    marksEt.setError("Enter marks");
                }
            }
        });
    }
}