package com.liurz.service;

import java.util.List;

import com.liurz.entity.UserLogin;

public interface UserLoginService {

	public void save(UserLogin userLogin);
	
	public List<UserLogin> getUserLogin(String userUuid,int page,int row);
	
}
