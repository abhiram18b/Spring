package com.example.springboot.restproject.dao;

import com.example.springboot.restproject.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> myQuery = entityManager.createQuery("FROM Employee",Employee.class);
        return myQuery.getResultList();
    }

    @Override
    public Employee findById(int id) {
        Employee employeeInDB = entityManager.find(Employee.class,id);
        return employeeInDB;
    }

    @Override
    public Employee save(Employee employee) {
        Employee employeeInDB = entityManager.merge(employee);
        return employeeInDB;
    }

    @Override
    public void deleteById(int id) {
        Employee employeeInDB = entityManager.find(Employee.class,id);
        entityManager.remove(employeeInDB);
    }
}
