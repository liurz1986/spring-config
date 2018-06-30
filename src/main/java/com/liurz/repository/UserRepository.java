package com.liurz.repository;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.liurz.entity.User;

//����ʹ�ø�ע��������˽ӿڲ���һ��Repository Bean

public interface UserRepository extends JpaRepository<User, String>{
	//jpa���Զ���ѯ������дsql��findBy��������������---�ͻ�������Բ�ѯ
	User findByUuid(String uuid);
	//jpa֧��hibernate������HQL
	@Query(value="from User where name=:name and password=:password")
	User findByNameAndPassword(@Param("name") String name,@Param("password") String password);
   
    //jpa�Է�ҳ֧��,name��ֵģ����ѯ
	@Query(value="select a from User a where a.name like %:name% ")
	Page<User> findAllByPageAndName(@Param("name") String name,Pageable pageable);
	
	/**
	 * jpa�Է�ҳ֧��,createTimeȥ��ѯ
	 * ?1��ʾ��һ����������2��ʾ�ڶ�������
	 */
	@Query(value="select u from User u where createTime between :startTime and :endTime order by createTime desc")
	Page<User> findAllByCreateTime(@Param("startTime")Date startTime,@Param("endTime")Date endTime,Pageable pageable);
	
	/**
	 * %m---��  00-12
	 * %Y---�� ����---2018  ��λ
	 * %y---�� ����      ---18 ��λ
	 * �ö�����ܷ������м����ᱨ������ȫ���ֶη���
	 */
	@Query(value="select * from t_user a  where DATE_FORMAT(a.create_time,'%m')=:month order by create_time desc limit :page,:row",nativeQuery=true)
	List<User> findAllByMonth(@Param("month") Integer month,@Param("page") Integer page,@Param("row") Integer row);
	
	/**
	 * ����ԭ��sql��@Modifyingʹ�����޸����ݱ�����
	 */
	@Modifying
	@Query(value="insert ignore into t_user(uuid,name,password) values(:uuid,:name,:password)",nativeQuery=true)
	void savaUser(@Param("uuid") String uuid,@Param("name") String name,@Param("password") String password);
	
	void deleteByUuid(String uuid);
	
	@Modifying
	@Query(value="delete from User where name=:name and password=:password")
	void deleteUserByNameAndPassord(@Param("name")String name,@Param("password")String password);
	

    @Modifying
    @Query(value="update User set status=:status where uuid=:uuid")
    void updateUserLogin(@Param("uuid") String uuid,@Param("status") int status);
}