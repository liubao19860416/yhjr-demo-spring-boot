package com.yhjr.demo.domain;

import java.util.Date;

/**
 * 测试实体
 * 
 * @author LiuBao
 * @version 2.0 2017年3月27日
 * 
 */
public class TestEntity {
	private Long id;
	private String userName;
	private String password;
	private String type;
	private boolean delFlag;
	private Date createDatetime;

	public TestEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	public String toString() {
		return "TestEntity [id=" + id + ", userName=" + userName
				+ ", password=" + password + ", type=" + type
				+ ", createDatetime=" + createDatetime + ", delFlag=" + delFlag
				+ "]";
	}

}
