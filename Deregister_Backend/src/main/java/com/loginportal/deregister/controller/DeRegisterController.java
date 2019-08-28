package com.loginportal.deregister.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.loginportal.deregister.model.Authenticate;
import com.loginportal.deregister.model.Deactivation;
import com.loginportal.deregister.model.User;
import com.loginportal.deregister.model.Reviews;
import com.loginportal.deregister.service.DeRegisterService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/users")
public class DeRegisterController {

	public DeRegisterController() {
		System.out.println("Welcome");
	}

	@Autowired
	private DeRegisterService deRegisterService;
	@Autowired
	ObjectMapper mapper;

	@GetMapping("/getreviews")
	public List<Reviews> listofreviews() {
		return deRegisterService.getComments();
	}

	@GetMapping("/daily")
	public void dailyActivity() {

		deRegisterService.changeToPurge();
		deRegisterService.deletePurgeUser();
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/deactivate")
	public ObjectNode deactivateUser(@RequestBody Authenticate authenticate) throws Exception {

		// String password = authenticate.getPassword();
		System.out.println("inside controller");
		ObjectNode objectNode = mapper.createObjectNode();

		try {
			User user = deRegisterService.authenticateUser(authenticate);
			if (user != null) {
				Boolean updateStatus = deRegisterService.updateUserStatus(user);

				if (updateStatus) {
					deRegisterService.changeNameInReviews(authenticate.getUserId());
					Deactivation deactivateUser = deRegisterService.deactivateUser(authenticate);
					if (deactivateUser != null) {
						objectNode.put("status", 200);
						objectNode.put("message", "Success");
					} else {
						objectNode.put("status", 301);
						objectNode.put("message", "DbError");
					}
				} else {
					objectNode.put("status", 301);
					objectNode.put("message", "DbError");
				}
			} else {
				objectNode.put("status", 400);
				objectNode.put("message", "Wrong credentials");
			}
		} catch (Exception e) {
			objectNode.put("status", 400);
			objectNode.put("message", "Wrong credentialshsakcjdw");
		}

		return objectNode;

	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/forgetuser")
	public ObjectNode forgetUser(@RequestBody Authenticate authenticate) throws Exception {

		ObjectNode objectNode = mapper.createObjectNode();

		try {
			User user = deRegisterService.authenticateUser(authenticate);
			if (user != null) {
				Boolean forgetStatus = deRegisterService.forgetUser(user);
				System.out.println("fghfhjhh");
				if (forgetStatus) {
					System.out.println("fghfh");
					deRegisterService.deleteInReviews(authenticate.getUserId());

					objectNode.put("status", 200);
					objectNode.put("message", "Success");

				} else {
					objectNode.put("status", 301);
					objectNode.put("message", "DbError");
				}
			} else {
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
