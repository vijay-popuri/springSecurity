package com.example.vijay.springSecurityDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vijay.springSecurityDemo.model.Student;
import com.example.vijay.springSecurityDemo.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jser;

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

	public String verify(String email, String password) {
		Authentication auth = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		System.out.println(email + ": " + password);
		if (auth.isAuthenticated())
			return jser.generateToken(email);
		return "FAILURE";
	}

}
