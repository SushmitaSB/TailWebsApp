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

public class StudentForm extends AppCompatActivity {

    EditText nameEt, subEt, marksEt;
    Button button;
    private String name, sub, marks;
    private RealmManager realmManager;
    private Validation validation;
    private Realm realm;
    private boolean status;
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
        validation = new Validation(this, realm);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEt.getText().toString();
                sub = subEt.getText().toString();
                marks = marksEt.getText().toString();
                try {
                   status = validation.setFormValidation(realmManager, name, sub, marks, nameEt, subEt, marksEt);
                   if (status){
                        finish();
                   }
                }catch (Exception ex){
                    Toast.makeText(StudentForm.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}