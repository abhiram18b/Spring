package com.example.springboot.dataRest.dao;


import com.example.springboot.dataRest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
