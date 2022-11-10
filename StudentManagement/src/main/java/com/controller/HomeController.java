package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Student;
import com.service.StudentService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private StudentService studentService;
	// add mapping for "/list"

	@RequestMapping("/")
	public String listStudents(Model theModel) {

	// get Students details from db
		List<Student> theStudents = studentService.findAll();
		

		// add to the spring model
		theModel.addAttribute("Students", theStudents);

		return "list-Students";
	}

}
