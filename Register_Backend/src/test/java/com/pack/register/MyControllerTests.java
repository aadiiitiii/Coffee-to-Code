package com.pack.register;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.pack.register.controller.RegisterController;
import com.pack.register.model.Password;
import com.pack.register.model.SecurityAnswer;
import com.pack.register.model.SecurityQuestion;
import com.pack.register.model.User;
import com.pack.register.service.SecurityQuestionService;
import com.pack.register.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyControllerTests extends AbstractTest {


    @InjectMocks
    RegisterController myController;
	@MockBean
	UserService userservice;
   
	@MockBean 
	SecurityQuestionService securityquestionservice;
	
	
	User user =new User();
	User sameuser = new User();
    
    Password password= new Password();
    SecurityAnswer securityAns= new SecurityAnswer();
    
    Password samepassword= new Password();
    SecurityAnswer samesecurityAns= new SecurityAnswer();
    SecurityQuestion sq1 = new SecurityQuestion();
    SecurityQuestion sq2 = new SecurityQuestion();

    
    
    @Before
    public void setUp() {
    	super.setUp();
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
       user.setAccountCreationTime(null);
       
       Mockito.when(userservice.saveUser(user)).thenReturn(user);
       
       

      
       samepassword.setPwd1("pavani@23");
       samesecurityAns.setSecurityQueID1(1);
      samesecurityAns.setSecurityAnsID1("CTC");
       samesecurityAns.setSecurityQueID2(2);
       samesecurityAns.setSecurityAnsID2("DDLJ");
       sameuser.setFirstName("Pavani");
       sameuser.setLastName("Nemuri");
       sameuser.setEmailID("pavanjktteinemeeuri8@gmail.com");
       sameuser.setPhoneNo("1234567891");
       sameuser.setPasswordHistory(password);
       sameuser.setSecurityAns(securityAns);
       sameuser.setAccountCreationTime(null);
       
     // Mockito.when(userservice.saveUser(emptyuser)).thenReturn(null);
       
       
       sq1.setQuestionID(1);
       sq1.setQuestion("question1");
       sq2.setQuestionID(2);
       sq2.setQuestion("question2");
       List<SecurityQuestion> listquestion  = new ArrayList<SecurityQuestion>();
       listquestion.add(sq1);
       listquestion.add(sq2);
       Mockito.when(securityquestionservice.findAll()).thenReturn(listquestion);
       
       

       
    }
    
    @Test
    public void getQuestionsList() throws Exception {
       String uri = "/api/v1/users/securityquestions";
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
          .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
       
       //CASE 1 list is returned
       int status = mvcResult.getResponse().getStatus();
       assertEquals(200, status);
       String content = mvcResult.getResponse().getContentAsString();
       SecurityQuestion[] questionList =mapFromJson(content,SecurityQuestion[].class);
       assertTrue(questionList.length > 0);
       
       //CAse 2  Empty list is returned
       Mockito.when(securityquestionservice.findAll()).thenReturn(new ArrayList<SecurityQuestion>());
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
    	          .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
         content = mvcResult.getResponse().getContentAsString();
         questionList =mapFromJson(content,SecurityQuestion[].class);
        assertTrue(questionList.length== 0);
       
       //CASE 3  Data base not responding
       Mockito.when(securityquestionservice.findAll()).thenReturn(null);
       mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
 	          .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      content = mvcResult.getResponse().getContentAsString();
     // questionList =mapFromJson(content,SecurityQuestion[].class);
      assertTrue(questionList.length== 0);
       
    }
    
    @Test
    public void registerUser() throws Exception {
     
    
       
       //Case 1: Successfully Registered
       String inputJson = super.mapToJson(user);
       String uri = "/api/v1/users/register";
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    		   .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
             
       String content = mvcResult.getResponse().getContentAsString();
       //System.out.println(content);     
       assertEquals("{\"status\":200,\"message\":\"Success\"}",content);
       
       //Case 2 Database not responding    
       Mockito.when(userservice.saveUser(user)).thenReturn(null);
        inputJson = super.mapToJson(user);       
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    		      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();    
         content = mvcResult.getResponse().getContentAsString();
       // System.out.println(content);
        assertEquals("{\"status\":301,\"message\":\"DbError\"}",content);
        

        
     
//      // Not registered successfully  (Not able to test exception)
//        
//        if(sameuser.equals(user))
//        {
//        	Mockito.when(userservice.saveUser(user)).thenThrow(DataIntegrityViolationException.class);
//            inputJson = super.mapToJson(sameuser);
//
//            mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//        		      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//            content = mvcResult.getResponse().getContentAsString();
//            System.out.println(content);
//            assertEquals("{\"status\":400,\"message\":\"User Already Exists\"}",content);  
//        	
//        }
       
       
     
       
       
       
    }
    
}