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
import com.cesar.StudentsAPI.persistence.Student_repository;



@RestController
@RequestMapping("/students")
public class Student_controller {

	
	
	@GetMapping()
	public ResponseEntity<?> getAll(){
		
		// Get students from DB
		List<Student> students = repo.findAll();
		
		
		// If there are students...
		if ( ! students.isEmpty() ) {
			
			return ResponseEntity.ok( students );
		}
		
		// If not,
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		
		// Get student from DB
		Optional<Student> student = repo.findById(id);
		
		
		// If the student exists...
		if ( student.isPresent() ) {
			
			
			return ResponseEntity.ok( student.get() );
		}
		
		// If not,
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	@PostMapping()
	public ResponseEntity<?> create(@RequestBody Student student){
			
		// Make sure id is null to prevent resources updates 
		if ( student.getId() != null ) {
			
			student.setId(null);
		}
		
		// Update student by providing id from DB
		student = repo.save(student);		
		
		return ResponseEntity.status( HttpStatus.CREATED ).body( student );
	}
	
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Student updatedStudent){
		
		// If resource exists
		if ( repo.findById( id ).isPresent() ) {
			
			
			// Make sure resource id equals to path id to prevent its updated
			if ( updatedStudent.getId() != id ) {
				
				updatedStudent.setId( id );
			}
			
			
			// and update it
			return ResponseEntity.ok( repo.save( updatedStudent ));	
		}
		
		// If not,
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> patch(@PathVariable Long id, @RequestBody Student updatedFields){
		
		// Get student from DB
		Student student = repo.findById( id ).get();
		
		// If the resource exists...
		if ( student != null ) {
			
			// patch its fields
			student.setName( updatedFields.getName() );
		}
		
		// If not,
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		
		// Get student from DB
		Student student = repo.findById(id).get();
		
		// If resource exists...
		if ( repo.findById( id ) != null ) {
			
			repo.deleteById( id );
			
			return ResponseEntity.ok( student );
		}
		
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	@Autowired
	private Student_repository repo;
}
