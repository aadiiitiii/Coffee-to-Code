package com.loginportal.deregister.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.loginportal.deregister.model.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Long>{
	
	@Modifying
	@Query(value="update register set user_status = 2 where userid = :id")
	void changeFlag(long id);
	
	@Query(value="Delete from register where user_status=2")
	void deletePurge();
	

}
