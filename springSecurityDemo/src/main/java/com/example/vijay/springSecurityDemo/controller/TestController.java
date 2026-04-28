package com.example.vijay.springSecurityDemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.vijay.springSecurityDemo.model.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class TestController {
	
//	List<Student> list = new ArrayList<>(List.of(
//			new Student("vijay", "vijay@gmail.com",30)
//			));

	@GetMapping
	public ResponseEntity<String> getMsg(HttpServletRequest req) {
		return new ResponseEntity<String>("Welcome to ONO  ->"+req.getSession().getId(), HttpStatus.OK);
	}
	
	@GetMapping("csrf")
	public CsrfToken getcsrfToken(HttpServletRequest req) {
		return (CsrfToken) req.getAttribute("_csrf");
	}
	
//	@GetMapping("students")
//	public List<Student> getStudents(){
//		return list;
//	}
//	@PostMapping("students")
//	public Student addStudent(@RequestBody Student req){
//		 list.add(req);
//		 return req;
//	}

}
