package com.rafaelpiccolo.studentsagenda.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.rafaelpiccolo.studentsagenda.dao.StudentDAO;
import com.rafaelpiccolo.studentsagenda.model.Student;
import com.rafaelpiccolo.studentsagenda.view.adapter.StudentsListAdapter;

public class StudentsListView {

    private final StudentDAO dao = new StudentDAO();
    private final Context context;
    private StudentsListAdapter adapter;

    public StudentsListView(Context context) {
        this.context = context;
    }

    public void confirmDelete(@NonNull MenuItem item) {
        AlertDialog.Builder deleteDialog = new AlertDialog.Builder(context);
        deleteDialog.setTitle("Delete Student");
        deleteDialog.setMessage("Are you sure you want to delete this student?");
        deleteDialog.setPositiveButton("Yes", (dialog, which) -> {
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Student chosenStudent = adapter.getItem(menuInfo.position);
            deleteStudent(chosenStudent);
        });
        deleteDialog.setNegativeButton("No", null);
        deleteDialog.show();
    }

    public void reloadStudents() {
        adapter.reload(dao.all());
    }

    private void deleteStudent(Student student) {
        dao.delete(student);
        adapter.remove(student);
    }

    public void configureAdapter(@NonNull ListView studentsList) {
        adapter = new StudentsListAdapter(context);
        studentsList.setAdapter(adapter);
    }

}
