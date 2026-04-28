package com.example.vijay.springSecurityDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vijay.springSecurityDemo.model.Student;
import com.example.vijay.springSecurityDemo.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;

	private BCryptPasswordEncoder en = new BCryptPasswordEncoder(12);

	public Student reigsterStudent(Student req) {
		req.setPassword(en.encode(req.getPassword()));
		return repo.save(req);
	}

	public Student getStudentById(int id) {
		return repo.findById(id).orElse(null);
	}

	public List<Student> getStudents() {
		return repo.findAll();
	}

	public Student updateUserData(Student req) {
		return repo.saveAndFlush(req);
	}

}
