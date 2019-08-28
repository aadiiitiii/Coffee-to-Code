package com.loginportal.deregister.service;

import java.util.List;

import com.loginportal.deregister.model.Authenticate;
import com.loginportal.deregister.model.Deactivation;
import com.loginportal.deregister.model.Reviews;
import com.loginportal.deregister.model.User;

public interface DeRegisterService {
		public Deactivation deactivateUser(Authenticate authenticate);
		
		public boolean updateUserStatus(User user) throws Exception;
		
		public User authenticateUser(Authenticate authenticate) throws Exception;
		
		public boolean forgetUser(User user);
		
//		public String deleteUser(long id);

		public  boolean changeNameInReviews(long id);
		
		public List<Reviews> getComments();
//		
//		public void  deleteReview(long id);
//		
		public void changeToPurge();
//		
		public void deletePurgeUser();
		
		public boolean deleteInReviews(long id);
}
