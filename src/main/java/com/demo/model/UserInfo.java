package com.demo.model;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;

public class UserInfo  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public UserInfo() {
		super();
	}
	
	public UserInfo(String name, String pwd) {
		this.username = name;
		this.password = pwd;
	}
	
	public UserInfo(String name, String pwd, int age, String gender, String email, Date createDate) {
		this.username = name;
		this.password = pwd;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.createDate = createDate;
	}
	
	private String username;
	private String password;
	private int age;
	private String gender;
	private String email;
	private Date createDate;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean equals(UserInfo user) {
		if (null == user) {
			return false;
		}
		
		if (this.username.equals(user.username) && 
				this.password.equals(user.password)) {
			return true;
		}
		return false;
	}
	
	public static UserInfo getRandomUser() {
		UserInfo user = new UserInfo();
		/*user.setUsername("sfrtgsdfsdf");
		user.setPassword("uioijhkjhhk");
		user.setAge(30);
		user.setGender("hgjghjfg");
		user.setPhone("rthfg");
		user.setAddress("fdgdftujhty");*/
		return user;
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("{0}_{1}", this.username, this.password);
	}
}

