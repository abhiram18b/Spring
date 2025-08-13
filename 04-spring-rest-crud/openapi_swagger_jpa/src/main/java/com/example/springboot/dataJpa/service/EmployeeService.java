package com.example.springboot.dataJpa.service;

import com.example.springboot.dataJpa.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);
}
