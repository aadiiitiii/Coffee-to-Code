package com.pack.register.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pack.register.model.SecurityQuestion;
import com.pack.register.repository.SecurityQuestionRepository;



@Service(value = "securityQuestionService")
public class SecurityQuestionServiceImpl implements SecurityQuestionService {

	@Autowired
    @Qualifier(value = "securityQuestionRepository")
	SecurityQuestionRepository securityQuestionRepository;
	
	@Override
	public List<SecurityQuestion> findAll() {
		
		
		return securityQuestionRepository.findAll();
	}

}
