package com.rafaelpiccolo.studentsagenda.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.rafaelpiccolo.studentsagenda.R;
import com.rafaelpiccolo.studentsagenda.dao.StudentDAO;
import com.rafaelpiccolo.studentsagenda.model.Student;

public class StudentFormActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText mobileField;
    private EditText emailField;
    private Student student;
    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);
        setTitle("Student Form");

        initializeFields();
        configureSaveButton();

        Intent data = getIntent();
        student = (Student) data.getSerializableExtra("student");
        nameField.setText(student.getName());
        mobileField.setText(student.getMobile());
        emailField.setText(student.getEmail());
    }

    private void initializeFields() {
        nameField = findViewById(R.id.name_field);
        mobileField = findViewById(R.id.mobile_field);
        emailField = findViewById(R.id.email_field);
    }

    private void configureSaveButton() {
        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(v -> {
            //Student createdStudent = createStudent();
            //save(createdStudent);
            createStudent();
            dao.edit(student);
            finish();
        });
    }

    private void save(Student student) {
        dao.save(student);
        finish();
    }

    private void createStudent() {
        String name = nameField.getText().toString();
        String mobile = mobileField.getText().toString();
        String email = emailField.getText().toString();

        student.setName(name);
        student.setMobile(mobile);
        student.setEmail(email);
    }
}