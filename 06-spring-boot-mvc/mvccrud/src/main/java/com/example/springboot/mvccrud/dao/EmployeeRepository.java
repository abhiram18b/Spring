package com.example.springboot.mvccrud.dao;


import com.example.springboot.mvccrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    List<Employee> findAllByOrderByLastNameAsc();
}
