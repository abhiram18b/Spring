package com.example.crudapplication;

import com.example.crudapplication.dao.StudentDAO;
import com.example.crudapplication.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return (runner)->{
			createStudent(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO) {
		//create student
		System.out.println("Creating Student.....");
		Student tempStudent = new Student("John","Doe","johndoe@gmail.com");

		//persisting the student
		System.out.println("Saving Student.....");
		studentDAO.save(tempStudent);

		//display id of saved student
		System.out.println("Saved Student. Generated Id : "+tempStudent.getId());
	}

}
