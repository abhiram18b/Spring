package com.example.springboot.restproject.rest;

import com.example.springboot.restproject.entity.Employee;
import com.example.springboot.restproject.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private ObjectMapper objectMapper;
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService,ObjectMapper objectMapper){
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
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



    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId,@RequestBody Map<String,Object> patchPayload){
        Employee employeeInDB = employeeService.findById(employeeId);

        if(employeeInDB == null){
            throw new RuntimeException("Employee id not found - "+employeeId);
        }

        if(patchPayload.containsKey("id")){
            throw  new RuntimeException("Employee id not allowed in request body - "+employeeId);
        }

        Employee patchedEmployee = apply(employeeInDB,patchPayload);
        Employee updateEmployeeInDB = employeeService.save(patchedEmployee);

        return patchedEmployee;

    }

    @DeleteMapping("/employees/{employeeId}")
    public String removeEmployee(@PathVariable int employeeId){
        Employee employeeInDB = employeeService.findById(employeeId);
        if(employeeInDB == null){
            throw new RuntimeException("Employee id not found - "+employeeId);
        }
        employeeService.deleteById(employeeId);

        return "Deleted Employee with id - "+employeeId;
    }

    public Employee apply(Employee employee,Map<String,Object> patchPayload){
        ObjectNode employeeNode = objectMapper.convertValue(employee, ObjectNode.class);
        ObjectNode patchPayloadNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        System.out.println(employeeNode);
        System.out.println(patchPayloadNode);

        employeeNode.setAll(patchPayloadNode);

        return  objectMapper.convertValue(employeeNode,Employee.class);
    }
}
