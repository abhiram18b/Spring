package com.example.springboot.restproject.rest;

import com.example.springboot.restproject.dao.EmployeeDAO;
import com.example.springboot.restproject.entity.Employee;
import com.example.springboot.restproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee employeeInDB = employeeService.findById(employeeId);
        if(employeeInDB == null){
            throw  new RuntimeException("Employee id not found - "+ employeeId);
        }
        return employeeInDB;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(0);
        return employeeService.save(employee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee employeeInDB = employeeService.save(employee);
        return employeeInDB;
    }

    @DeleteMapping("/employees/{employeeId}")
    public void removeEmployee(@PathVariable int employeeId){
        employeeService.deleteById(employeeId);
    }
}
