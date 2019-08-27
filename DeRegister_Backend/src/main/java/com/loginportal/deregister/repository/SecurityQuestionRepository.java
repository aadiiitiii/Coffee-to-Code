package com.loginportal.deregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.loginportal.deregister.model.SecurityQuestion;



@Repository("securityQuestionRepository")

public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestion,Integer> {
	
	

}
