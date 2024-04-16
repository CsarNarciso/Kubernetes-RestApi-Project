package com.cesar.StudentsAPI.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesar.StudentsAPI.Entity.Student;
import com.cesar.StudentsAPI.persistence.Students_repository;

@RestController
@RequestMapping("/students")
public class Students_controller {

	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id){
		
		Optional<Student> student = repo.findById(id);
		
		if ( student.isPresent() ) {
			
			
			return ResponseEntity.ok( student.get() );
		}
		
		return ResponseEntity.noContent().build();
	}
	
	
	@Autowired
	private Students_repository repo;
}
