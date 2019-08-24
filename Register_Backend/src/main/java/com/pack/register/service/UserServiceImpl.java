package com.pack.register.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;


import com.pack.register.model.CustomPasswordEncoder;
import com.pack.register.model.Password;
import com.pack.register.model.User;
import com.pack.register.repository.UserRepository;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
	@Autowired
    @Qualifier(value = "userRepository")
    UserRepository userrepo;
	
	@Autowired 
	CustomPasswordEncoder cutompasswordencoder;
	
	
    public UserServiceImpl(UserRepository userrepo){
        this.userrepo = userrepo;
    }
    
	public User saveUser(User user) {
		

		String salt1 = BCrypt.gensalt(12);
		Password pwd = user.getPasswordHistory();
		String hashpwd1= cutompasswordencoder.encodeWithSalt(pwd.getPwd1(),salt1);
		
		pwd.setPwd1(hashpwd1);
		pwd.setSalt1(salt1);
		user.setPasswordHistory(pwd);
    	return userrepo.save(user);
	}

	 
}
