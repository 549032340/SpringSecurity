package com.example.domain;

public class User {

	private String loginName;
	private String password;
	private int age;
	private String sex;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "User [loginName=" + loginName + ", password=" + password + ", age=" + age + ", sex=" + sex + "]";
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String loginName, String password, int age, String sex) {
		super();
		this.loginName = loginName;
		this.password = password;
		this.age = age;
		this.sex = sex;
	}


}
