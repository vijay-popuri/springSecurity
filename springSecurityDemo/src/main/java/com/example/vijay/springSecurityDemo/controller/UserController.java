package com.example.vijay.springSecurityDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vijay.springSecurityDemo.model.Student;
import com.example.vijay.springSecurityDemo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService ser;

	@PostMapping("register")
	public Student register(@RequestBody Student req) {
		return ser.reigsterStudent(req);
	}

	@GetMapping("/{id}")
	public Student register(@PathVariable int id) {
		return ser.getStudentById(id);
	}

	@GetMapping
	public List<Student> getAllStudents() {
		return ser.getStudents();
	}

	@PostMapping("login")
	public String verify(
			@RequestParam String email, 
			@RequestParam String password) {
		return ser.verify(email, password);
	}

}
