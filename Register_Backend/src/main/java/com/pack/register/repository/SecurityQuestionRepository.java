package com.pack.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.register.model.SecurityQuestion;


@Repository("securityQuestionRepository")

public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestion,Integer> {

}
