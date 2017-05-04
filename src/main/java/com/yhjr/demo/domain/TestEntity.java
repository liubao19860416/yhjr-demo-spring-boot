package com.yhjr.demo.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 测试实体
 * 
 * @author LiuBao
 * @version 2.0 
 * 2017年3月27日
 */
@Alias("testEntity")
@XmlRootElement(name = "testEntity")  
public class TestEntity extends BaseEntity {
    private static final long serialVersionUID = -2339055596519569119L;
	private String userName;
	private String password;
	private String type;
	private Date createDatetime;

	public TestEntity() {
	}

	@XmlElement
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

}
