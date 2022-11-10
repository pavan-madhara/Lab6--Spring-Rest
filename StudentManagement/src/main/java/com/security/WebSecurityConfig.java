package com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.service.MyUserDetailsService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	     
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.myUserDetailsService).passwordEncoder(passwordEncoder());
	}
	 

	 
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
			http.cors().disable();
			http.csrf().disable();
			http.headers().frameOptions().disable();
	        http.authorizeRequests()
			    .antMatchers("/h2-console/**").permitAll()
	            .antMatchers(HttpMethod.GET, "/","/student/save","/student/showFormForAdd","/student/403").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
	            .antMatchers(HttpMethod.GET, "/student/showFormForUpdate","/student/delete").hasAuthority("ROLE_ADMIN")
	            .antMatchers(HttpMethod.PUT, "/student/showFormForUpdate","/student/delete").hasAuthority("ROLE_ADMIN")
	            .antMatchers(HttpMethod.POST, "/student/showFormForUpdate","/student/delete").hasAuthority("ROLE_ADMIN")
	            .antMatchers(HttpMethod.DELETE, "/student/showFormForUpdate","/student/delete").hasAuthority("ROLE_ADMIN")
	    		.anyRequest().authenticated()
	    		.and()
	    		.httpBasic()
	    		.and()
	    		.formLogin();
	    }

}
