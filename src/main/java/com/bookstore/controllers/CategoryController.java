package com.bookstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.entity.TaiKhoan;
import com.bookstore.service.BookService;
import com.bookstore.service.CategoryService;
import com.bookstore.service.TaiKhoanService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService cateser;
	
	@Autowired
	private BookService bookser;
	
	@Autowired
	private TaiKhoanService tkSer;
	
	@RequestMapping("/category/{id}")
	public ModelAndView getMyList(@PathVariable("id")int id,Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	TaiKhoan tk=tkSer.findByUserName(username);
    	//System.out.println(tk.getIsSell());
//    	TaiKhoan tk=new TaiKhoan();
//    	tk=tkSer.findByUserName(username);
        model.addAttribute("tk", tk);
		Category c=cateser.getCateId(id);
		int id1=c.getId();
		List<Category> list1 = cateser.getAllCategory();
		Book latestBook = bookser.getBookMaxId();
		List<Book> b=bookser.getBookByCateId(c.getId());
		ModelAndView m=new ModelAndView("bookList");
		m.addObject("book",b);
		m.addObject("cate", list1);
		m.addObject("l", latestBook);
		m.addObject("tag", id1);
		return m;
	}
}
