package com.pack.register.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.register.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Integer>{
	

}
