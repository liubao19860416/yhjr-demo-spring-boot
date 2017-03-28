package com.yhjr.demo.vo;

import java.io.Serializable;

/**
 * 测试请求参数实体
 * 
 * @author LiuBao
 * @version 2.0 
 * 2017年3月27日
 */
public class TestParam implements Serializable {
	private static final long serialVersionUID = 4127004632562874805L;
	private String userId;
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "TestParam [userId=" + userId + ", password=" + password + "]";
	}

}
