package com.pack.register.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pack.register.model.SecurityQuestion;
import com.pack.register.model.User;
import com.pack.register.service.SecurityQuestionService;
import com.pack.register.service.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/users")
public class RegisterController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityQuestionService securityquestionService;
	@Autowired
	ObjectMapper mapper;

	@GetMapping(value = "/securityquestions")
	public List<SecurityQuestion> securityquestions() {

		return securityquestionService.findAll();
	}

	@GetMapping(value = "/getallusers")
	public List<User> getAllUsers() {
		System.out.println(new User());
		System.out.println("Inside getallusers");
		return userService.findAll();
	}

	@PostMapping(path = "/register")
	public ResponseEntity<?> saveProduct(@RequestBody User user) {

//		ObjectNode objectNode = mapper.createObjectNode();

		try {
			userService.saveUser(user);
			return ResponseEntity.status(HttpStatus.OK).body("User Successfully Registered");
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User Already Exists");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service Unavailable");
		}
	}

}
