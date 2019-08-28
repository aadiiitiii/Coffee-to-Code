package com.loginportal.deregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.loginportal.deregister.model.User;


@Repository("authenticateRepository")
public interface AuthenticateRepository extends JpaRepository<User,Long>{
	
	@Modifying
	@Query(value="UPDATE register set user_status = :userStatus  where userid = :id", nativeQuery = true)
	void changeUserStatus(int userStatus,long id);
	
	@Modifying
	@Query(value="Delete from register where user_status=3", nativeQuery = true)
	void deletePurge();

}
