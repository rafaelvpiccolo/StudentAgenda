package com.rafaelpiccolo.studentsagenda.view;

import static com.rafaelpiccolo.studentsagenda.view.ActivitiesConstants.STUDENT_KEY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

        initializeFields();
        loadStudent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_student_form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemID = item.getItemId();
        if(itemID == R.id.save_student_menu) {
            endForm();
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadStudent() {
        Intent data = getIntent();
        if (data.hasExtra(STUDENT_KEY)){
            setTitle("Edit Student");
            student = (Student) data.getSerializableExtra(STUDENT_KEY);
            fillFields();
        } else {
            setTitle("New Student");
            student = new Student();
        }
    }

    private void fillFields() {
        nameField.setText(student.getName());
        mobileField.setText(student.getMobile());
        emailField.setText(student.getEmail());
    }

    private void initializeFields() {
        nameField = findViewById(R.id.name_field);
        mobileField = findViewById(R.id.mobile_field);
        emailField = findViewById(R.id.email_field);
    }

    private void endForm() {
        createStudent();
        if(student.hasValidID()) {
            dao.edit(student);
        }
        else {
            dao.save(student);
        }
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