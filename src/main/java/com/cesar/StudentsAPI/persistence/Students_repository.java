package com.cesar.StudentsAPI.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cesar.StudentsAPI.Entity.Student;

public interface Students_repository extends JpaRepository<Student, Integer>{

	List<Student> findAll();
}
