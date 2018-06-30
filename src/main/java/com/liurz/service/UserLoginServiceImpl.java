package com.liurz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liurz.entity.UserLogin;
import com.liurz.repository.UserLoginRepository;
@Service
@Transactional
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserLoginRepository  userLoginRepository;
	public void save(UserLogin userLogin) {
		
		userLoginRepository.save(userLogin);
		
	}

	public List<UserLogin> getUserLogin(String userUuid,int page,int row) {
		// TODO Auto-generated method stub
		if(page>0&&row>0){
			Pageable pageable=new PageRequest(page-1,row);
			return userLoginRepository.findUserLoginByUserUuidAndSessionId(userUuid, pageable).getContent();
		}else{
			return userLoginRepository.findUserLoginByUserUuidAndSessionId(userUuid);
		}
		
	}

}
