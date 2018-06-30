package com.liurz.entity;

import java.util.Date;
/**
 * �û���
 * @author Administrator
 *
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "t_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	// Ψһ��ʶ������Ϊ�� nullable�����ǲ��ǿ���Ϊ�գ�Ĭ��ʱtrue������Ϊ�գ�length����������󳤶�
	@Column(name = "uuid", nullable = false, length = 40)
	private String uuid;

	@Column(name = "name", nullable = false, length = 30)
	private String name;

	@Column(name = "password", nullable = false, length = 50)
	private String password;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") // ʱ������ɸ�ʽ
	@CreationTimestamp // ����ʱ���
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") // ʱ������ɸ�
	@UpdateTimestamp // ����ʱ���
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	@Column(name = "status", nullable = false, length = 10)
	private int status = 0;// Ĭ����0---�����У�1---�ǳ�(����)

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

   
   
}
