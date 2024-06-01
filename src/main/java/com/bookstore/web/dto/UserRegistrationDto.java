package com.bookstore.web.dto;

public class UserRegistrationDto {
	
	private String username;
	private String password;
	private String repeatPassword;
	private String hovaten;
	private String email;
	private int isSell;
	private int isAdmin;
	public UserRegistrationDto(String username, String password, String hovaten, String email,int isSell,int isAdmin) {
		super();
		this.username = username;
		this.password = password;
		this.hovaten = hovaten;
		this.email = email;
		this.isSell=isSell;
		this.isAdmin=isAdmin;
	}
	public UserRegistrationDto() {
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
	public String getHovaten() {
		return hovaten;
	}
	public void setHovaten(String hovaten) {
		this.hovaten = hovaten;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIsSell() {
		return isSell;
	}
	public void setIsSell(int isSell) {
		this.isSell = isSell;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getRepeatPassword() {
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
	

}
