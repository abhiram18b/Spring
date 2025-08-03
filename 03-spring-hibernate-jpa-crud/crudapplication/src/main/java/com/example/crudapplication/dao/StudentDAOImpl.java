package com.example.crudapplication.dao;

import com.example.crudapplication.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{
    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(int id){
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> myQuery = entityManager.createQuery("FROM Student",Student.class);

        return myQuery.getResultList();
    }

    @Override
    public List<Student> findAllSortedByLastName() {
        TypedQuery<Student> myQuery = entityManager.createQuery("FROM Student ORDER BY lastName",Student.class);
        return myQuery.getResultList();
    }

    @Override
    public List<Student> findStudentsByLastName(String lastName){
        TypedQuery<Student> myQuery = entityManager.createQuery("FROM Student WHERE lastName = :theData",Student.class);
        myQuery.setParameter("theData",lastName);
        return myQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(int id){
        Student student = entityManager.find(Student.class,id);
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteAll(){
        int modifiedRows = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return modifiedRows;
    }
}
