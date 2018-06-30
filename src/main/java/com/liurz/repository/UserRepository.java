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

//必须使用该注解标明，此接口不是一个Repository Bean

public interface UserRepository extends JpaRepository<User, String>{
	//jpa会自动查询，不用写sql。findBy后面接类的属性名---就会根据属性查询
	User findByUuid(String uuid);
	//jpa支持hibernate，采用HQL
	@Query(value="from User where name=:name and password=:password")
	User findByNameAndPassword(@Param("name") String name,@Param("password") String password);
   
    //jpa对分页支持,name的值模糊查询
	@Query(value="select a from User a where a.name like %:name% ")
	Page<User> findAllByPageAndName(@Param("name") String name,Pageable pageable);
	
	/**
	 * jpa对分页支持,createTime去查询
	 * ?1表示第一个参数，？2表示第二个参数
	 */
	@Query(value="select u from User u where createTime between :startTime and :endTime order by createTime desc")
	Page<User> findAllByCreateTime(@Param("startTime")Date startTime,@Param("endTime")Date endTime,Pageable pageable);
	
	/**
	 * %m---月  00-12
	 * %Y---年 比如---2018  四位
	 * %y---年 比如      ---18 两位
	 * 用对象接受返回其中几个会报错，必须全部字段返回
	 */
	@Query(value="select * from t_user a  where DATE_FORMAT(a.create_time,'%m')=:month order by create_time desc limit :page,:row",nativeQuery=true)
	List<User> findAllByMonth(@Param("month") Integer month,@Param("page") Integer page,@Param("row") Integer row);
	
	/**
	 * 采用原生sql，@Modifying使用在修改数据表的情况
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