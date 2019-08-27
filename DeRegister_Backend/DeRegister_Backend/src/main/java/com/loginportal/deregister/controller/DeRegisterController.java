package com.loginportal.deregister.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loginportal.deregister.model.Reviews;
import com.loginportal.deregister.service.DeRegisterService;




@RestController
@CrossOrigin(origins = "*")
@EnableScheduling
@RequestMapping("/api/v1/users")
public class DeRegisterController {

	@Autowired
	private DeRegisterService deRegisterService;

	
	@Autowired
	ObjectMapper mapper;
	
	@Scheduled(cron = "0 30 10 ? * MON-FRI")
	public void dailyActivity() {
		
		deRegisterService.changeToPurge();
		deRegisterService.deletePurgeUser();
	}
	
	
	
	@DeleteMapping(value="/deregister")
	public String deleteUser(@RequestBody long userId) {
		removeReview(userId);
		return deRegisterService.deleteUser(userId);
	}

	
	@PutMapping(value = "/reviews")
	public String deactivateUser(@RequestBody long userId) {
		
		try{
			System.out.println("1st come "+userId);
			deRegisterService.changeName(userId);
			
			return "Success";
		}
		catch (Exception e) {
			
			 return e.getMessage();
		}		
		
	}
	
	@DeleteMapping(value="/reviews")
	public String removeReview(@RequestBody long userId) {
		try {
			deRegisterService.deleteReview(userId);
			return "Deleted successfully";
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@GetMapping(value="/reviews")
	public List<Reviews> listofreviews(){
		System.out.println("Welcome");
		return  deRegisterService.getComments();
	}
	
}

