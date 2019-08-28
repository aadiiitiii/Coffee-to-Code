package com.pack.register.service;


import java.util.List;

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
    UserRepository userRepository;
	
	@Autowired 
	CustomPasswordEncoder cutompasswordencoder;
	
	
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
	public User saveUser(User user) {
		

		String salt1 = BCrypt.gensalt(12);
		Password pwd = user.getPasswordHistory();
		String hashpwd1= cutompasswordencoder.encodeWithSalt(pwd.getPwd1(),salt1);
		pwd.setPwd1(hashpwd1);
		pwd.setSalt1(salt1);
		user.setPasswordHistory(pwd);
    	return userRepository.save(user);
	}
	
	@Override
	public List<User> findAll() {
		
		return userRepository.findAll();
	}

	 
}
