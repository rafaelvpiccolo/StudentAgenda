package com.rafaelpiccolo.studentsagenda.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rafaelpiccolo.studentsagenda.R;
import com.rafaelpiccolo.studentsagenda.dao.StudentDAO;

public class StudentsListActivity extends AppCompatActivity {

    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        setTitle("Students List");

        configureFAB();
    }

    @Override
    protected void onResume() {
        super.onResume();
        configureList();
    }

    public void configureFAB() {
        FloatingActionButton fab = findViewById(R.id.add_student_fab);
        fab.setOnClickListener(v -> {
            openForm();
        });
    }

    public void configureList() {
        ListView studentsList = findViewById(R.id.students_list);

        studentsList.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                dao.all()));
    }

    private void openForm() {
        startActivity(new Intent(this, StudentFormActivity.class));
    }

}