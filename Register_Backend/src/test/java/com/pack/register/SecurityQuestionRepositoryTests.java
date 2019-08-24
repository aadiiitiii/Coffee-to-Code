package com.pack.register;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pack.register.model.SecurityQuestion;
import com.pack.register.repository.SecurityQuestionRepository;



@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class SecurityQuestionRepositoryTests {
	
	@Autowired
    @Qualifier(value = "securityQuestionRepository")
	SecurityQuestionRepository securityQuestionRepository;
	
	  SecurityQuestion sq1 = new SecurityQuestion();
	  SecurityQuestion sq2 = new SecurityQuestion();
	  List<SecurityQuestion> listquestion  = new ArrayList<SecurityQuestion>();

	@Before
    public void setUp() {
    	
		  sq1.setQuestionID(1);
	       sq1.setQuestion("question1");
	       sq2.setQuestionID(2);
	       sq2.setQuestion("question2");
	       listquestion.add(sq1);
	       listquestion.add(sq2);
       

    }
	
	 @Test
	  public void securityQuestionList()  {

		 //Case1: Getting  the questions from   DB
		 
		 securityQuestionRepository.save(sq1);
		 securityQuestionRepository.save(sq2);

		 List<SecurityQuestion> temp = securityQuestionRepository.findAll();
		 assertEquals(listquestion,temp);
		
		 

		
	 }   	
	
	

}
