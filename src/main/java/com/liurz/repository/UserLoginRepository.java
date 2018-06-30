package com.liurz.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.liurz.entity.UserLogin;
//����ʹ�ø�ע��������˽ӿڲ���һ��Repository Bean,JPA 1.5�汾���ϣ����ӻᱨ��

public interface UserLoginRepository extends JpaRepository<UserLogin, Integer>{
	
	//jpa
	@Query(value="select a from UserLogin a where a.userUuid=:userUuid  order by loginTime desc")
	Page<UserLogin> findUserLoginByUserUuidAndSessionId(@Param("userUuid")String userUuid,Pageable page);
	
	//jpa֧��hibernate������HQL
    @Query(value="from UserLogin where userUuid=:userUuid order by loginTime desc")
    List<UserLogin> findUserLoginByUserUuidAndSessionId(@Param("userUuid")String userUuid);
    
}