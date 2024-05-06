package com.cesar.StudentsAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesar.StudentsAPI.persistence.Student_repository;

@Service
public class Student_service {

	
	
	@Autowired
	private Student_repository repo;
}
