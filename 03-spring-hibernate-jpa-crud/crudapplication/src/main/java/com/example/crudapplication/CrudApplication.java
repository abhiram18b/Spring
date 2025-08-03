package com.example.crudapplication;

import com.example.crudapplication.dao.StudentDAO;
import com.example.crudapplication.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return (runner)->{
//			createStudent(studentDAO);
//			createMultipleStudents(studentDAO);
//			readStudent(studentDAO);
//			getAllStudents(studentDAO);
//			getAllStudentsSortedByLastName(studentDAO);
			getStudentsByLastName(studentDAO);
		};
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		//create student
		System.out.println("Creating Students.....");
		Student tempStudent1 = new Student("steve","rogers","steve@gmail.com");
		Student tempStudent2 = new Student("bruce","wayne","wayne@gmail.com");
		Student tempStudent3 = new Student("clark","kent","clark@gmail.com");

		//persisting the student
		System.out.println("Saving Students.....");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

		//display id of saved student
		System.out.println("Saved Students.");
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

	private void readStudent(StudentDAO studentDAO) {
		System.out.println("Creating Student......");
		Student newStudent = new Student("virat","kohli","vk18@gmail.com");

		System.out.println("Saving Student......");
		studentDAO.save(newStudent);

		int theId = newStudent.getId();
		System.out.println("Saved Student. Generated Id : "+theId);

		System.out.println("Retrieving Student with Id : "+theId);
		Student myStudent = studentDAO.findById(theId);

		System.out.println("Retrieved Student");
		System.out.println(myStudent);
	}

	public void getAllStudents(StudentDAO studentDAO){
		List<Student> students = studentDAO.findAll();

		for(Student s: students){
			System.out.println(s);
		}
	}

	public void getAllStudentsSortedByLastName(StudentDAO studentDAO){
		List<Student> students = studentDAO.findAllSortedByLastName();

		for(Student s: students){
			System.out.println(s);
		}
	}

	public void getStudentsByLastName(StudentDAO studentDAO){
		List<Student> students = studentDAO.findStudentsByLastName("wayne");

		for(Student s: students){
			System.out.println(s);
		}

	}

}
