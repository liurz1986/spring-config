package com.liurz.service;

import com.liurz.entity.User;

public interface UserService {

	public void save(User user);
	
	public User getUser(String name,String password);
	
	public void updateUser(String uuid,int status);
	
}
