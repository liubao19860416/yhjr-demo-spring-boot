package com.yhjr.demo.vo;

import java.io.Serializable;

/**
 * 测试VO实体类
 * 
 * @author LiuBao
 * @version 2.0 
 * 2017年3月27日
 */
public class TestResultVO implements Serializable {
	private static final long serialVersionUID = -2451145299328946340L;
	private String userName;
	private String password;
	private Long age;
	private TestParam testParam;

	public TestParam getTestParam() {
		return testParam;
	}

	public void setTestParam(TestParam testParam) {
		this.testParam = testParam;
	}

	public void setAge(Long age) {
		this.age = age;
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

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "TestResultVO [userName=" + userName + ", password=" + password + ", age=" + age + ", testParam="
				+ testParam + "]";
	}

}
