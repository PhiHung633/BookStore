package com.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Category;
import com.bookstore.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository caterepo;
	
	public List<Category> getAllCategory()
	{
		return caterepo.findAll();
	}
	public Category getCateId(int id)
	{
		return caterepo.findById(id).get();
	}
}
