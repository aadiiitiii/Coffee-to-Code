package com.loginportal.deregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loginportal.deregister.model.Authenticate;
import com.loginportal.deregister.model.User;

@Repository("authenticateRepository")
public interface AuthenticateRepository extends JpaRepository<User,Long>{

}
