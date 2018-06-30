package com.liurz.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * ���ڵ�¼��¼��
 * @author Administrator
 *
 */
@Entity
@Table(name="t_userloign")
public class UserLogin {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="userUuid",nullable=false,length=40)
	private String userUuid;//�û���uuid
	@Column(name="sessionId",nullable=false,length=40)
	private String sessionId;//sessionId
	
	
	
	private Date loginTime;//��¼ʱ��
	
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")//ʱ������ɸ�ʽ
	@CreationTimestamp//����ʱ���
	@Temporal(TemporalType.TIMESTAMP)
	private Date logoutTime;//�ǳ�ʱ��
	//���������ݿ⽻����ֻ����¼����
	@Transient
	private User user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserUuid() {
		return userUuid;
	}
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Date getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
