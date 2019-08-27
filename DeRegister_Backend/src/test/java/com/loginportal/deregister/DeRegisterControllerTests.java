package com.loginportal.deregister;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.loginportal.deregister.controller.DeRegisterController;
import com.loginportal.deregister.model.User;
import com.loginportal.deregister.service.DeRegisterService;
import com.loginportal.deregister.model.Authenticate;
import com.loginportal.deregister.model.Deactivation;
import com.loginportal.deregister.model.Password;
import com.loginportal.deregister.model.SecurityAnswer;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeRegisterControllerTests extends AbstractTest{

	@InjectMocks 
	DeRegisterController deRegisterController;
	
	@MockBean
	DeRegisterService deRegisterService;
	
	Authenticate authenticate = new Authenticate();
	Deactivation deactivateUser = new Deactivation();
	User user = new User();
	  
    Password password= new Password();
    SecurityAnswer securityAns= new SecurityAnswer();
    
    Date date = new Date();	
    private Timestamp time = new Timestamp(date.getTime());
	
    @Before
	public void setup() {
		super.setUp();
	    
		authenticate.setUserId(1);
		authenticate.setPassword("xyz");
		deactivateUser.setUserId(1);
		 password.setPwd1("pavani@23");
	       securityAns.setSecurityQueID1(1);
	       securityAns.setSecurityAnsID1("CTC");
	       securityAns.setSecurityQueID2(2);
	       securityAns.setSecurityAnsID2("DDLJ");
	       user.setFirstName("Pavani");
	       user.setLastName("Nemuri");
	       user.setEmailID("pavanjktteinemeeuri8@gmail.com");
	       user.setPhoneNo("1234567891");
	       user.setPasswordHistory(password);
	       user.setSecurityAns(securityAns);
	       user.setAccountCreationTime(time);
		
	}
	
	@Test
	public void testdeactivate() throws Exception{
		 String uri = "/api/v1/users/deactivate";
		 System.out.println(user);
		 System.out.println(authenticate);
		 
		 //case 3:deactivation done successfully
	     Mockito.when(deRegisterService.authenticateUser(authenticate)).thenReturn(user);
	     Mockito.when(deRegisterService.updateUserStatus(user)).thenReturn(true);
	     Mockito.when(deRegisterService.deactivateUser(authenticate)).thenReturn(deactivateUser);
	     String inputJson = super.mapToJson(authenticate);
	     MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
   		      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn(); 
	     String content = mvcResult.getResponse().getContentAsString();
	    assertEquals("{\"status\":200,\"message\":\"Success\"}",content);
	    
	    //case 2: user doesn't exist
	    Mockito.when(deRegisterService.authenticateUser(authenticate)).thenReturn(null);
	   inputJson = super.mapToJson(authenticate);
	   mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
  		      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn(); 
	    content = mvcResult.getResponse().getContentAsString();
	    assertEquals("{\"status\":400,\"message\":\"Wrong credentials\"}",content);
	      
	    //case 3:When user status can't be updated 
	    Mockito.when(deRegisterService.authenticateUser(authenticate)).thenReturn(user);
	    Mockito.when(deRegisterService.updateUserStatus(user)).thenReturn(false);
		   inputJson = super.mapToJson(authenticate);
		   mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	  		      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn(); 
		    content = mvcResult.getResponse().getContentAsString();
		    assertEquals("{\"status\":301,\"message\":\"DbError\"}",content);
		    
		//case 4: When entry can't be made into deactivation table
		   Mockito.when(deRegisterService.authenticateUser(authenticate)).thenReturn(user);
		   Mockito.when(deRegisterService.updateUserStatus(user)).thenReturn(true);  
		   Mockito.when(deRegisterService.deactivateUser(authenticate)).thenReturn(null);
		   inputJson = super.mapToJson(authenticate);
		   mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	  		      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn(); 
		    content = mvcResult.getResponse().getContentAsString();
		    assertEquals("{\"status\":301,\"message\":\"DbError\"}",content);
	    
	}
	
}
