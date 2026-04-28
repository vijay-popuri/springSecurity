package com.example.vijay.springSecurityDemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vijay.springSecurityDemo.model.Student;

public interface UserRepo extends JpaRepository<Student, Integer> {
	Student findByEmail(String email);
}
