package com.liurz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liurz.entity.User;
import com.liurz.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    
	@Autowired
	private UserRepository userRepository;
	public void save(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

	public User getUser(String name, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByNameAndPassword(name, password);
	}

	public void updateUser(String uuid, int status) {
		// TODO Auto-generated method stub
		userRepository.updateUserLogin(uuid, status);
	}

}
