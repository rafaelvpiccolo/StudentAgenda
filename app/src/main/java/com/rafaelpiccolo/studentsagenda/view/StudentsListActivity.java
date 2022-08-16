package com.rafaelpiccolo.studentsagenda.view;

import static com.rafaelpiccolo.studentsagenda.view.ActivitiesConstants.STUDENT_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rafaelpiccolo.studentsagenda.R;
import com.rafaelpiccolo.studentsagenda.dao.StudentDAO;
import com.rafaelpiccolo.studentsagenda.model.Student;
import com.rafaelpiccolo.studentsagenda.view.adapter.StudentsListAdapter;

public class StudentsListActivity extends AppCompatActivity {

    private final StudentDAO dao = new StudentDAO();
    private StudentsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        setTitle("Students List");

        configureFAB();
        configureList();
        dao.save(new Student("Rafael", "19983663497", "rafaelvpiccolo@gmail.com"));
        dao.save(new Student("Agatha", "19983663497", "agathabertolani@gmail.com"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadStudents();
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
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Student chosenStudent = adapter.getItem(menuInfo.position);
            deleteStudent(chosenStudent);
        }
        return super.onContextItemSelected(item);
    }

    private void reloadStudents() {
        adapter.reload(dao.all());
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
        adapter = new StudentsListAdapter(this);
        studentsList.setAdapter(adapter);
    }

    private void openFormToAdd() {
        startActivity(new Intent(this, StudentFormActivity.class));
    }

}