package com.rafaelpiccolo.studentsagenda.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rafaelpiccolo.studentsagenda.R;
import com.rafaelpiccolo.studentsagenda.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsListAdapter extends BaseAdapter {
    private final List<Student> students = new ArrayList<>();
    private final Context context;

    public StudentsListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Student getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return students.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View createdView = getInflate(parent);
        Student student = students.get(position);
        setInfo(createdView, student);
        return createdView;
    }

    private void setInfo(View view, Student student) {
        TextView name = view.findViewById(R.id.student_name_item);
        name.setText(student.getName());
        TextView mobile = view.findViewById(R.id.student_mobile_item);
        mobile.setText(student.getMobile());
    }

    private View getInflate(ViewGroup parent) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.student_item, parent, false);
    }

    public void reload(List<Student> students) {
        this.students.clear();
        this.students.addAll(students);
        notifyDataSetChanged();
    }

    public void remove(Student student) {
        students.remove(student);
        notifyDataSetChanged();
    }
}
