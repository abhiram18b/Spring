package com.example.crudapplication.dao;

import com.example.crudapplication.entity.Student;

public interface StudentDAO {
    void save(Student student);

    Student findById(int id);
}
