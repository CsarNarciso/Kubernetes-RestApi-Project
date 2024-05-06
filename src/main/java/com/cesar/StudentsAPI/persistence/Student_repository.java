package com.cesar.StudentsAPI.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cesar.StudentsAPI.Entity.Student;

public interface Student_repository extends JpaRepository<Student, Long>{

	List<Student> findAll();
}
