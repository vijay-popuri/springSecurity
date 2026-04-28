package com.example.vijay.springSecurityDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.vijay.springSecurityDemo.service.MyUserDtlsService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecuritConfig {

	@Autowired
	private MyUserDtlsService ser;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {

		return http.csrf(c -> c.disable())
				.authorizeHttpRequests(req -> req.requestMatchers("/login").permitAll().anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
	}

//	@Bean
//	public UserDetailsService userDetails() {
//		UserDetails user1 = User.withDefaultPasswordEncoder().username("vijay").password("123").roles("USER").build();
//
//		UserDetails user2 = User.withDefaultPasswordEncoder().username("raju").password("123").roles("USER").build();
//
//		return new InMemoryUserDetailsManager(user1, user2);
//	}

	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider dp = new DaoAuthenticationProvider(ser); // ✅ correct for your version
		dp.setPasswordEncoder(passwordEncoder());
		return dp;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12); // use BCrypt in real apps
	}

	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}
}
