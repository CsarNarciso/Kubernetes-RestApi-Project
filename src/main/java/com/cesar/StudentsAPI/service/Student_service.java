package com.cesar.StudentsAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesar.StudentsAPI.Entity.Student;
import com.cesar.StudentsAPI.persistence.Student_repository;

@Service
public class Student_service {

	
	
	public List<Student> getAll(){
		
		return repo.findAll();	
	}
	
	
	public Optional<Student> getById(Long id) {
		
		return repo.findById(id);
	}
	
	
	public Student create(Student student) {
		
		// Make sure id is null to prevent resources updates 
		if ( student.getId() != null ) {
			
			student.setId(null);
		}
		
		// Update student by providing id from DB
		student = repo.save(student);	
		
		
		return repo.save(student);
	}
	
	
	public Student update(Long id, Student student) {
		
		// If resource exists
		if ( repo.findById( id ).isPresent() ) {
					
					
			// Make sure resource id equals to path id to prevent its updated
			if ( student.getId() != id ) {
				
				student.setId( id );
			}
			
			
			// and update it
			return repo.save( student );	
		}
		
		//If not,
		return null;
	}
	
	
	public Student patch(Long id, Student updatedFields) {
		
		// Get student from DB
		Student student = repo.findById( id ).get();
		
		// If the resource exists...
		if ( student != null ) {
			
			// patch its fields
			student.setName( updatedFields.getName() );
			
			return repo.save(student);
		}
		
		//If not,
		return null;
	}
	
	
	public void delete(Long id) {
		
		repo.deleteById( id );
	}
	
	
	
	
	
	@Autowired
	private Student_repository repo;
}
