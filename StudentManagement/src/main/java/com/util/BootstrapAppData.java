package com.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.entity.Role;
import com.entity.Student;
import com.entity.User;
import com.repository.StudentRepository;
import com.repository.UserRepository;



@Component
public class BootstrapAppData {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	

	@Bean
	public BCryptPasswordEncoder passwordEncoder1() {
		return new BCryptPasswordEncoder();
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void insertStudentData(ApplicationReadyEvent event) {
		
		// Creating Dummy data in database
		Student student = new Student();
		student.setFirstName("Ramesh");
		student.setLastName("Patel");
		student.setCourse("Java");
		student.setCountry("Africa");
		
		Student student1 = new Student();
		student1.setFirstName("Virat");
		student1.setLastName("Kohli");
		student1.setCourse("Cricket");
		student1.setCountry("India");
		
		
		this.studentRepository.save(student);
		this.studentRepository.save(student1);
		
	}

	
	@EventListener(ApplicationReadyEvent.class)
	public void insertRolesData(ApplicationReadyEvent event) {
		
		// Creating application ready users in database
		User admin = new User();
		admin.setUsername("admin");
		admin.setPassword(passwordEncoder1().encode("admin"));
		
		User user = new User();
		user.setUsername("user");
		user.setPassword(passwordEncoder1().encode("user"));
		
		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		
		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");		

		admin.addRole(adminRole);
		user.addRole(userRole);
		
		this.userRepository.save(admin);
		this.userRepository.save(user);
		
	}


}
