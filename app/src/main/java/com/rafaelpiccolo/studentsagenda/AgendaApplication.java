package com.rafaelpiccolo.studentsagenda;

import android.app.Application;

import com.rafaelpiccolo.studentsagenda.dao.StudentDAO;
import com.rafaelpiccolo.studentsagenda.model.Student;

public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        createTestStudents();
    }

    private void createTestStudents() {
        StudentDAO dao = new StudentDAO();
        dao.save(new Student("Rafael", "19983663497", "rafaelvpiccolo@gmail.com"));
        dao.save(new Student("Agatha", "19983663497", "agathabertolani@gmail.com"));
    }
}
