package com.loginportal.deregister.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.annotation.PropertySource;

import com.loginportal.deregister.model.Reviews;
import com.loginportal.deregister.repository.DeRegisterRepository;
import com.loginportal.deregister.repository.ReviewRepository;
import com.loginportal.deregister.repository.UserRepository;


@PropertySource("myresources.properties")
public class DeRegisterServiceImplementation implements DeRegisterService{

	@Autowired
    @Qualifier(value = "userRepository")
    UserRepository userrepo;

	@Qualifier(value = "reviewRepository")
	ReviewRepository reviewRepository;
	
	@Qualifier(value = "deRegisterRepository")
	DeRegisterRepository deRegisterRepository;
	
	@Override
	public String deleteUser(long id) {		
		
		try {
			userrepo.deleteById(id);
			return "Success";
		}
		catch (Exception e) {
			return e.getMessage();
		}
		
		
		
	}

	@Transactional
	@Override
	public String changeName(long id) {
		try {
			System.out.println("Welcome");
			String msg = "anonymous";
			reviewRepository.changeName(id, msg);
			
			return "Success";
		}
		catch (Exception e) {
			return e.getMessage();
		}
		
	}

	@Override
	public List<Reviews> getComments() {
		try {
			return reviewRepository.findAll();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	
		
	}

	@Transactional
	@Override
	public String deleteReview(long id) {
		System.out.println(id);
		try {
			reviewRepository.deleteByUid(id);
			return "done";
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return "error";
		
	}

	@Transactional
	@Override
	public void changeToPurge() {
		List<Long> useridList = deRegisterRepository.findDeactivated();
		for(long userid : useridList)
		{
			userrepo.changeFlag(userid);
		}
	}

	@Override
	public void deletePurgeUser() {
		userrepo.deletePurge();
		
	}
	
	

}
