package com.pack.register;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.pack.register.model.CustomPasswordEncoder;
import com.pack.register.model.SecurityQuestion;
import com.pack.register.repository.SecurityQuestionRepository;
import com.pack.register.service.SecurityQuestionService;
import com.pack.register.service.SecurityQuestionServiceImpl;



@RunWith(SpringRunner.class)
public class SecurityQuestionServiceTests {

	@TestConfiguration
    static class DataServiceTestContextConfiguration {
        @Bean
        public SecurityQuestionService securityQuestionService() {
            return new SecurityQuestionServiceImpl();
        }
    }
    
	@MockBean
	@Qualifier(value = "securityQuestionRepository")
	static SecurityQuestionRepository secqurityquestionrepository;
	
	@MockBean 
	CustomPasswordEncoder cutompasswordencoder;
	@Autowired
	private SecurityQuestionService securityQuestionService;
	
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
    	
       
       Mockito.when(securityQuestionService.findAll()).thenReturn(listquestion);
      

    }
	
	 @Test
	    public void ListSecurityQuestion()  {
		 
		 // Case1: Getting List successfully
		 List<SecurityQuestion> temp = securityQuestionService.findAll();
		 assertEquals(listquestion,temp); 
	 }   	

}
