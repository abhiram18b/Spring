package com.example.crudapplication.dao;

import com.example.crudapplication.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student student);

    Student findById(int id);

    List<Student> findAll();

    List<Student> findAllSortedByLastName();

    List<Student> findStudentsByLastName(String lastName);
}
