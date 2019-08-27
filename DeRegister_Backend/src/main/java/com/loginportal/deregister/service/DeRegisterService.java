package com.loginportal.deregister.service;

import com.loginportal.deregister.model.Authenticate;
import com.loginportal.deregister.model.Deactivation;
import com.loginportal.deregister.model.User;

public interface DeRegisterService {
		public Deactivation deactivateUser(Authenticate authenticate);
		
		public boolean updateUserStatus(User user) throws Exception;
		
		public User authenticateUser(Authenticate authenticate) throws Exception;
}
