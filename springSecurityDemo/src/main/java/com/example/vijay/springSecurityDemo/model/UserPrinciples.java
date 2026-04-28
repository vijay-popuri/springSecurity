package com.example.vijay.springSecurityDemo.model;

import java.util.Collection;
import java.util.Collections;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrinciples implements UserDetails {

	private Student st;

	public UserPrinciples(Student st) {
		this.st = st;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public @Nullable String getPassword() {
		return st.getPassword();
	}

	@Override
	public String getUsername() {
		return st.getEmail();
	}

}
