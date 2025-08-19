package com.example.springmvc.thymeleafdemo.controller;

import com.example.springmvc.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class StudentFormController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;

    @Value("${systems}")
    private List<String> systems;

    @RequestMapping("/showStudentForm")
    public String showStudentForm(Model theModel){
        Student theStudent = new Student();
        theModel.addAttribute("student",theStudent);
        theModel.addAttribute("countries",countries);
        theModel.addAttribute("languages",languages);
        theModel.addAttribute("systems",systems);
        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processStudentForm(@ModelAttribute("student")Student theStudent){

        System.out.println("theStudent = "+theStudent.getFirstName()+" "+theStudent.getLastName());
        System.out.println(theStudent.getFavouriteSystems());
        return "student-confirmation";
    }
}
