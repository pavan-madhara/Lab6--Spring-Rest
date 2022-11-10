package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;
import com.security.MyUserDetails;



@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = this.userRepository.findByUsername(username);
		//Logging current user credentials
		System.out.println("***************************************");
		System.out.println(optionalUser.get());
		if(optionalUser.isPresent()) {
			User user = optionalUser.get(); 
			return new MyUserDetails(user);
		}
		throw new UsernameNotFoundException("Could not found username");
	}

}
