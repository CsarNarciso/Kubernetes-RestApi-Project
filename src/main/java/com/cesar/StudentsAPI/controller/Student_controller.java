package com.cesar.StudentsAPI.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesar.StudentsAPI.Entity.Student;
import com.cesar.StudentsAPI.service.Student_service;



@RestController
@RequestMapping("/students")
public class Student_controller {

	
	
	@GetMapping()
	public ResponseEntity<?> getAll(){
		
		// Get students from Service
		List<Student> students = service.getAll();
		
		
		// If there are students...
		if ( ! students.isEmpty() ) {
			
			return ResponseEntity.ok( students );
		}
		
		// If not,
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		
		// Get student from Service
		Optional<Student> student = service.getById(id);
		
		
		// If the student exists...
		if ( student.isPresent() ) {
			
			
			return ResponseEntity.ok( student.get() );
		}
		
		// If not,
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	@PostMapping()
	public ResponseEntity<?> create(@RequestBody Student student){
			
		// Update student by providing id from DB
		student = service.create(student);		
		
		return ResponseEntity.status( HttpStatus.CREATED ).body( student );
	}
	
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Student updatedStudent){
		
		if ( service.update(id, updatedStudent) != null ) {
			
			
			return ResponseEntity.ok( updatedStudent );	
		}
		
		// If not,
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> patch(@PathVariable Long id, @RequestBody Student updatedFields){
		
		Student patchedStudent = service.patch(id, updatedFields);
		
		//If the resource exists...
		if ( patchedStudent != null ) {
			
			return ResponseEntity.ok( service.update(id, updatedFields) );
		}
		
		// If not,
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	@Autowired
	private Student_service service;
}
