package com.example.springboot.mvccrud.service;

import com.example.springboot.mvccrud.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);
}
