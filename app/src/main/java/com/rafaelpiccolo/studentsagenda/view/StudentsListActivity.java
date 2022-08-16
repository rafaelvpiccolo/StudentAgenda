package com.rafaelpiccolo.studentsagenda.view;

import static com.rafaelpiccolo.studentsagenda.view.ActivitiesConstants.STUDENT_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rafaelpiccolo.studentsagenda.R;
import com.rafaelpiccolo.studentsagenda.model.Student;

public class StudentsListActivity extends AppCompatActivity {
    StudentsListView studentsListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        studentsListView = new StudentsListView(this);
        setTitle("Students List");
        configureFAB();
        configureList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentsListView.reloadStudents();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_students_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int menuID = item.getItemId();
        if(menuID == R.id.delete_menu) {
            studentsListView.confirmDelete(item);
        }
        return super.onContextItemSelected(item);
    }

    public void configureFAB() {
        FloatingActionButton fab = findViewById(R.id.add_student_fab);
        fab.setOnClickListener(v -> openFormToAdd());
    }

    public void configureList() {
        ListView studentsList = findViewById(R.id.students_list);

        studentsListView.configureAdapter(studentsList);
        configureItemClickListener(studentsList);
        registerForContextMenu(studentsList);
    }

    private void configureItemClickListener(ListView studentsList) {
        studentsList.setOnItemClickListener((parent, view, position, id) -> {
            Student chosenStudent = (Student) parent.getItemAtPosition(position);
            openFormToEdit(chosenStudent);
        });
    }

    private void openFormToEdit(Student student) {
        Intent goToForm = new Intent(StudentsListActivity.this, StudentFormActivity.class);
        goToForm.putExtra(STUDENT_KEY, student);
        startActivity(goToForm);
    }

    private void openFormToAdd() {
        startActivity(new Intent(this, StudentFormActivity.class));
    }
}