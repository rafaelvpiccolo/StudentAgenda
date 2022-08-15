package com.rafaelpiccolo.studentsagenda.view;

import static com.rafaelpiccolo.studentsagenda.view.ActivitiesConstants.STUDENT_KEY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rafaelpiccolo.studentsagenda.R;
import com.rafaelpiccolo.studentsagenda.dao.StudentDAO;
import com.rafaelpiccolo.studentsagenda.model.Student;

import java.util.List;

public class StudentsListActivity extends AppCompatActivity {

    private final StudentDAO dao = new StudentDAO();
    private ArrayAdapter<Student> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        setTitle("Students List");

        configureFAB();
        configureList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadStudents();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Delete");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Student chosenStudent = adapter.getItem(menuInfo.position);
        deleteStudent(chosenStudent);
        return super.onContextItemSelected(item);
    }

    private void reloadStudents() {
        adapter.clear();
        adapter.addAll(dao.all());
    }

    public void configureFAB() {
        FloatingActionButton fab = findViewById(R.id.add_student_fab);
        fab.setOnClickListener(v -> {
            openFormToAdd();
        });
    }

    public void configureList() {
        ListView studentsList = findViewById(R.id.students_list);

        configureAdapter(studentsList);
        configureItemClickListener(studentsList);
        registerForContextMenu(studentsList);
    }

    private void deleteStudent(Student student) {
        dao.delete(student);
        adapter.remove(student);
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

    private void configureAdapter(ListView studentsList) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        studentsList.setAdapter(adapter);
    }

    private void openFormToAdd() {
        startActivity(new Intent(this, StudentFormActivity.class));
    }

}