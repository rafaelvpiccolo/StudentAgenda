package com.rafaelpiccolo.studentsagenda.dao;

import androidx.annotation.NonNull;

import com.rafaelpiccolo.studentsagenda.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final static List<Student> students = new ArrayList<>();

    private static int idCounter = 1;

    public void save(Student student) {
        student.setId(idCounter);
        students.add(student);
        idCounter++;
    }

    public void edit(Student student) {
        Student foundStudent = null;
        for (Student s : students) {
            if(s.getId() == student.getId()) {
                foundStudent = s;
            }
        }
        if (foundStudent != null ) {
            int studentPosition = students.indexOf(foundStudent);
            students.set(studentPosition, student);
        }
    }

    public List<Student> all() {
        return new ArrayList<>(students);
    }
}
