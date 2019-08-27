package com.loginportal.deregister.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.loginportal.deregister.model.Authenticate;
import com.loginportal.deregister.model.Deactivation;
import com.loginportal.deregister.model.User;
import com.loginportal.deregister.service.DeRegisterService;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/api/v1/users")
public class DeRegisterController {

	@Autowired
	private DeRegisterService deRegisterService;
	@Autowired
	ObjectMapper mapper;

	/*@CrossOrigin(origins="*")
	@PostMapping(value = "/deactivate")
	public ObjectNode authenticateUser(@RequestBody Authenticate authenticate) throws Exception {
		
		//String password = authenticate.getPassword();
		System.out.println("inside controller");
		ObjectNode objectNode = mapper.createObjectNode();
		
		try {
			User user = deRegisterService.authenticateUser(authenticate);
			if(user!=null) {
				try {
				Boolean updateStatus = deRegisterService.updateUserStatus(user);
				if(updateStatus) {
					try {
					Deactivation deactivateUser=deRegisterService.deactivateUser(authenticate);
					if(deactivateUser!=null) {
						objectNode.put("status", 200);
						objectNode.put("message", "Success");
					}
					else {
						objectNode.put("status", 301);
						objectNode.put("message", "DbError");
					}
					}catch(Exception e) {
						objectNode.put("status", 301);
						objectNode.put("status", "DbError");
					}
				}
				else {
					objectNode.put("status", 301);
					objectNode.put("message", "DbError");
				}
				}catch(Exception e){
					objectNode.put("status", 301);
					objectNode.put("status", "DbError");
				}
			}
			else {
				objectNode.put("status", 400);
				objectNode.put("message", "Wrong Credentials");
			}

		}catch(NoSuchElementException e) {
			objectNode.put("status", 400);
			objectNode.put("message", "Wrong Credentials");
		}
		return objectNode;
		
	}*/
	
	@CrossOrigin(origins="*")
	@PostMapping(value = "/deactivate")
	public ObjectNode authenticateUser(@RequestBody Authenticate authenticate) throws Exception {
		
		//String password = authenticate.getPassword();
		System.out.println("inside controller");
		ObjectNode objectNode = mapper.createObjectNode();
		
		try {
			User user = deRegisterService.authenticateUser(authenticate);
			if(user!=null) {
				Boolean updateStatus = deRegisterService.updateUserStatus(user);
				if(updateStatus) {
					Deactivation deactivateUser = deRegisterService.deactivateUser(authenticate);
					if(deactivateUser!=null) {
						objectNode.put("status", 200);
						objectNode.put("message", "Success");
					}
					else {
						objectNode.put("status", 301);
						objectNode.put("message", "DbError");
					}
				}
				else {
					objectNode.put("status", 301);
					objectNode.put("message", "DbError");
				}
			}
			else {
				objectNode.put("status", 400);
				objectNode.put("message", "Wrong credentials");
			}
		} catch (Exception e) {
			objectNode.put("status", 400);
			objectNode.put("message", "Wrong credentialshsakcjdw");
		}
		
		return objectNode;
		
	}
}
