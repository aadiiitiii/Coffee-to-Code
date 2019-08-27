package com.loginportal.deregister.service;

import java.util.List;

import com.loginportal.deregister.model.Reviews;


public interface DeRegisterService {
	
	String deleteUser(long id);

	String changeName(long id);
	
	List<Reviews> getComments();
	
	String deleteReview(long id);
	
	void changeToPurge();
	
	void deletePurgeUser();
}