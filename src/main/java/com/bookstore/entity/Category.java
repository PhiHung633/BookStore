package com.bookstore.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String name;
	
	@OneToMany(mappedBy = "category", fetch=FetchType.LAZY)
    private List<Book> books;
	

	public Category() {
		super();
	}
	

	public Category(int id, String name) {
		super();
		Id = id;
		this.name = name;
	}


	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
