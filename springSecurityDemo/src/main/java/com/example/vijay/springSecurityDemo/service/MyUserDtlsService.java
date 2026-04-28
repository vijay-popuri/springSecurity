package com.example.vijay.springSecurityDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.vijay.springSecurityDemo.model.Student;
import com.example.vijay.springSecurityDemo.model.UserPrinciples;
import com.example.vijay.springSecurityDemo.repo.UserRepo;

@Service
public class MyUserDtlsService implements UserDetailsService {

	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Student s = repo.findByEmail(username);
		if (s == null)
			throw new UsernameNotFoundException("No UserFound");
		return new UserPrinciples(s);
	}
}
