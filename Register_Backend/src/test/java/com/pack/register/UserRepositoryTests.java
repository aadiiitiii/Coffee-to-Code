package com.pack.register;

import static org.junit.Assert.assertEquals;
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

import com.pack.register.model.Password;
import com.pack.register.model.SecurityAnswer;
import com.pack.register.model.User;
import com.pack.register.repository.UserRepository;



@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class UserRepositoryTests {
	
	User user =new User();
    User emptyuser = new User();
    Password password= new Password();
    SecurityAnswer securityAns= new SecurityAnswer();
    
	@Autowired
	@Qualifier(value = "userRepository")
	private UserRepository userrepo;
	
	@Before
    public void setUp() {
    	//super.setUp();
	
       password.setPwd1("pavani@23");
       password.setSalt1("$2a$12$2jDJzTrQ9UOP43LVEyrdweLBe10SA0csWa5EzsHlQm0suxaWv7UqS");
       securityAns.setSecurityQueID1(1);
       securityAns.setSecurityAnsID1("CTC");
       securityAns.setSecurityQueID2(2);
       securityAns.setSecurityAnsID2("DDLJ");
       user.setFirstName("Pavani");
       user.setLastName("Nemuri");
       user.setEmailID("pavaninemsdfsduri8@gmail.com");
       user.setPhoneNo("1234567891");
       user.setPasswordHistory(password);
       user.setSecurityAns(securityAns);
       user.setAccountCreationTime(null);

    }
	
	 @Test
	  public void registerUser()  {

		 //Case1: Saving the user in DB
		 User tempuser = userrepo.save(user);
		
		 assertEquals(user,tempuser);
		
		 //Case 2: Already user existing it does not insert but returns the saved user 
		 userrepo.save(user);
		 assertEquals(user,tempuser);

		
	 }   	

  }


