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
public class DetailController {
	@Autowired
	private BookService bookSer;
	
	@Autowired
	private CategoryService cateSer;
	
	@Autowired
	private TaiKhoanService tkSer;
	
	@RequestMapping("/detail/{id}")
	public ModelAndView getMyList(@PathVariable("id")int id,Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	TaiKhoan tk=tkSer.findByUserName(username);
    	//System.out.println(tk.getIsSell());
//    	TaiKhoan tk=new TaiKhoan();
//    	tk=tkSer.findByUserName(username);
        model.addAttribute("tk", tk);
		Book b = bookSer.getBookById(id);
		List<Category> list1 = cateSer.getAllCategory();
		Book latestBook = bookSer.getBookMaxId();
		ModelAndView m=new ModelAndView("detail");
		m.addObject("cate", list1);
		m.addObject("l", latestBook);
		m.addObject("book",b);
		return m;
	}

}
