package com.loginportal.deregister.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.loginportal.deregister.model.Authenticate;
import com.loginportal.deregister.model.CustomPasswordEncoder;
import com.loginportal.deregister.model.Deactivation;
import com.loginportal.deregister.model.User;
import com.loginportal.deregister.model.Reviews;
import com.loginportal.deregister.model.UserStatus;
import com.loginportal.deregister.repository.AuthenticateRepository;
import com.loginportal.deregister.repository.DeactivateRepository;
import com.loginportal.deregister.repository.ReviewRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;


@Service(value = "deRegisterService")
public class DeRegisterServiceImpl implements DeRegisterService{

	@Autowired 
	CustomPasswordEncoder cutompasswordencoder;
	
	@Autowired
	@Qualifier("authenticateRepository")
	AuthenticateRepository authenticateRepository;
	
	@Autowired
	@Qualifier("deactivateRepository")
	DeactivateRepository deactivateRepository;
	
	@Autowired
	@Qualifier(value = "reviewRepository")
	ReviewRepository reviewRepository;
	
	@Override
	public Deactivation deactivateUser(Authenticate authenticate) {
		
				Deactivation deActivateUser = new Deactivation();
				deActivateUser.setUserId(authenticate.getUserId());
				return deactivateRepository.save(deActivateUser);
		
	}
	
	public boolean updateUserStatus(User user){
		user.setUserStatus(UserStatus.DEACTIVE);
		   try {
			User newUser =authenticateRepository.save(user);
			if(newUser!=null) {
				return true;
			}
			}catch(Exception e) {
				return false;
			}
		   
		return false;
	}
	
	public User authenticateUser(Authenticate authenticate){
		String password = authenticate.getPassword();
		 try {
			Optional<User> u= authenticateRepository.findById(authenticate.getUserId());
			if(u!=null) {
				User user = u.get();
				String hashpwd= cutompasswordencoder.encodeWithSalt(password,user.getPasswordHistory().getSalt1());
				if(hashpwd.equals(user.getPasswordHistory().getPwd1())) {
					return user;
				}
			}
			}catch(NoSuchElementException e) {
				return null;
			}
		 
		return null;
	}
	
//	@Override
//	public String deleteUser(long id) {		
//		
//		try {
//			authenticateRepository.deleteById(id);
//			return "Success";
//		}
//		catch (Exception e) {
//			return e.getMessage();
//		}
//		
//		
//		
//	}

	
	@Transactional
	@Override
	public  boolean changeNameInReviews(long id) {
		try {
			System.out.println("Welcome");
			String msg = "Anonymous";
			reviewRepository.changeName(id, msg);
			
			return true;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
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
//
//	@Transactional
//	@Override
//	public void deleteReview(long id) {
//		System.out.println(id);
//		try {
//			reviewRepository.deleteByUid(id);
//			
//		}
//		catch (Exception e) {
//			System.out.println(e);
//		}
//		
//		
//	}
//
	@Transactional
	@Override
	public void changeToPurge() {
		System.out.println("Came Inside");
		List<Long> useridList =  deactivateRepository.findDeactivated();
		
		for(long userid : useridList)
		{
			System.out.println(userid);
			authenticateRepository.changeUserStatus(3, userid);
		}
	}
//
	@Transactional
	@Override
	public void deletePurgeUser() {
		authenticateRepository.deletePurge();
		
	}

	@Override
	public boolean forgetUser(User user) {
		
		user.setUserStatus(UserStatus.PURGE);
		   try {
			User newUser =authenticateRepository.save(user);
			if(newUser!=null) {
				return true;
			}
			}catch(Exception e) {
				return false;
			}
		   
		return false;
	}

	@Transactional
	@Override
	public boolean deleteInReviews(long id) {
		try {
			reviewRepository.deleteByUid(id);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	

}




