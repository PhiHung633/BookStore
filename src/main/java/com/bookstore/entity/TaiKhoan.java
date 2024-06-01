package com.bookstore.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TaiKhoan {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	private String username;
	private String password;
	private String hovaten;
	private String email;
	private int isSell;
	private int isAdmin;
	@OneToMany(mappedBy = "taiKhoan", fetch = FetchType.LAZY)
    private List<Book> books;
	
	
	public TaiKhoan() {
	}
	public TaiKhoan(String username,String password,String hovaten,String email,int isSell,int isAdmin)
	{
		super();
		this.username = username;
		this.password = password;
		this.hovaten = hovaten;
		this.email=email;
		this.isSell=isSell;
		this.isAdmin=isAdmin;
	}
	public TaiKhoan(int id, String username, String password, String hovaten,String email,int isSell,int isAdmin) {
		super();
		Id = id;
		this.username = username;
		this.password = password;
		this.hovaten = hovaten;
		this.email=email;
		this.isSell=isSell;
		this.isAdmin=isAdmin;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
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
	

}
