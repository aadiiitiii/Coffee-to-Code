package com.loginportal.deregister.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.loginportal.deregister.model.Authenticate;
import com.loginportal.deregister.model.CustomPasswordEncoder;
import com.loginportal.deregister.model.Deactivation;
import com.loginportal.deregister.model.User;
import com.loginportal.deregister.model.UserStatus;
import com.loginportal.deregister.repository.AuthenticateRepository;
import com.loginportal.deregister.repository.DeactivateRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

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
}


