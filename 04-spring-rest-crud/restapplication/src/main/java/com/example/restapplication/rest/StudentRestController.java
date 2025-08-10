package com.example.restapplication.rest;

import com.example.restapplication.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    List<Student> theStudents;

    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Poornima","Patel"));
        theStudents.add(new Student("Mario","Rossi"));
        theStudents.add(new Student("Marie","Smith"));
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return  theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId){
        if((studentId >= theStudents.size()) || studentId < 0)
        {
            throw new StudentNotFoundException("Student id not found - "+studentId);
        }
        return theStudents.get(studentId);
    }


}
