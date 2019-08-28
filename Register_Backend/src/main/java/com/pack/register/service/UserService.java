package com.pack.register.service;

import java.util.List;

import com.pack.register.model.User;

public interface UserService {
	public User saveUser(User user);
	public List<User> findAll();

}
