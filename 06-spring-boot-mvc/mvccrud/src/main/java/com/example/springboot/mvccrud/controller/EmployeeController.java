package com.example.springboot.mvccrud.controller;

import com.example.springboot.mvccrud.entity.Employee;
import com.example.springboot.mvccrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String  listEmployees(Model theModel){
        List<Employee> employees = employeeService.findAll();
        System.out.println(employees);
        theModel.addAttribute("employees",employees);
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        theModel.addAttribute("employee",new Employee());

        return "employees/employee-form";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute("employee") Employee employee){
        Employee newEmployee = employeeService.save(employee);
        return "redirect:/employees/list";
    }
}
