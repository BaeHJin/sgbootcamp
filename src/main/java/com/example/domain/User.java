package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity	//db와 연결해주는
public class User {
	
	@Id @GeneratedValue //고유한 아이디
	private long id;
	
	@Column(length = 15, nullable = false, unique = true)
	private String userId;
	
	@Column(length = 15, nullable = false, unique = true)
	private String password;
	
	@Column(length = 15, nullable = false, unique = true)
	private String name;
	
	@Column(length = 30, nullable = true, unique = true)
	private String email;
		
	public User(){};
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User(long id, String userId, String password, String name, String email) {
		super();
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
	
	public Boolean checkPassword(String password){
		return this.password.equals(password);
	}
	
	public void update(User user){
		if(checkPassword(user.password)){
			this.name = user.name;
			this.email = user.email;
		}
	}

	public boolean checkId(long id) {
		return this.id == id;
	}
	public boolean checkUserId(User user) {
		return this.userId.equals(user.getUserId());
	}
	
}
