package com.bookstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.entity.TaiKhoan;
import com.bookstore.service.BookService;
import com.bookstore.service.CategoryService;
import com.bookstore.service.TaiKhoanService;

@Controller
public class SearchController {
	
	@Autowired
	private BookService bookser;
	
	@Autowired
	private CategoryService cateSer;
	
	@Autowired 
	private TaiKhoanService tkSer;
	
	@RequestMapping("/search")
	public ModelAndView getListSearch(@RequestParam(name="txt") String txtSearch,Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	TaiKhoan tk=tkSer.findByUserName(username);
    	//System.out.println(tk.getIsSell());
//    	TaiKhoan tk=new TaiKhoan();
//    	tk=tkSer.findByUserName(username);
        model.addAttribute("tk", tk);
		List<Book> list=bookser.getBookBySearch(txtSearch);
		List<Category> list1 = cateSer.getAllCategory();
		Book latestBook = bookser.getBookMaxId();
		ModelAndView m = new ModelAndView("bookList");
		m.addObject("book", list);
		m.addObject("cate", list1);
		m.addObject("l", latestBook);
		return m;
	}

}
